package com.senai.gerenciamento_epi.controller;

import com.senai.gerenciamento_epi.dto.*;
import com.senai.gerenciamento_epi.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class CLIController implements CommandLineRunner {

    private final ColaboradorService colabService;
    private final EpiService epiService;
    private final EmprestimoService empService;
    private final Scanner input = new Scanner(System.in);

    // Construtor Manual (Sem Lombok)
    public CLIController(ColaboradorService colabService,
                         EpiService epiService,
                         EmprestimoService empService) {
        this.colabService = colabService;
        this.epiService = epiService;
        this.empService = empService;
    }

    @Override
    public void run(String... args) {
        int opcao = 0;
        do {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE EPI ===");
            System.out.println("1. Novo Colaborador");
            System.out.println("2. Listar Colaboradores");
            System.out.println("3. Editar Colaborador");
            System.out.println("4. Inativar Colaborador");
            System.out.println("-EPI-------------------------------");
            System.out.println("5. Novo EPI");
            System.out.println("6. Listar EPIs");
            System.out.println("7. Editar EPI");
            System.out.println("8. Baixa em EPI (Indisponivel)");
            System.out.println("-EMPRESTIMO------------------------");
            System.out.println("9. Realizar Emprestimo");
            System.out.println("10. Realizar Devolucao");
            System.out.println("11. Listar Emprestimos Ativos");
            System.out.println("0. Sair");

            System.out.print("Escolha uma opcao: ");

            try {
                opcao = input.nextInt();
                input.nextLine(); // Limpa o buffer do teclado

                switch (opcao) {
                    case 1 -> criarColab();
                    case 2 -> listarColab();
                    case 3 -> editarColab();
                    case 4 -> inativarColab();
                    case 5 -> criarEpi();
                    case 6 -> listarEpi();
                    case 7 -> editarEpi();
                    case 8 -> baixarEpi();
                    case 9 -> emprestar();
                    case 10 -> devolver();
                    case 11 -> listarAtivos();
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("Opcao Invalida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("ERRO CRÍTICO NO SISTEMA: " + e.getMessage());
                input.nextLine(); // Limpa o buffer em caso de erro de digitação
            }
        } while (opcao != 0);
    }

    // --- METODOS AUXILIARES ---

    private void criarColab() {
        System.out.println("\n--- NOVO COLABORADOR ---");
        System.out.print("Nome: ");
        String n = input.nextLine();
        if (n.isBlank()) { System.out.println("Nome não pode ser vazio!"); return; }
        System.out.print("Setor: ");
        String s = input.nextLine();

        ColaboradorDTO novo = colabService.criar(new ColaboradorDTO(n, s, "1"));
        System.out.println("Colaborador salvo com sucesso!");
        System.out.println(novo);
    }

    private void listarColab() {
        System.out.println("\n--- LISTA DE COLABORADORES ---");
        colabService.listar().forEach(System.out::println);
    }

    private void editarColab() {
        System.out.println("\n--- EDITAR COLABORADOR ---");
        System.out.print("ID do Colaborador: ");
        int id = input.nextInt();
        input.nextLine();
        if (id <= 0) { System.out.println("ID Inválido!"); return; }

        System.out.print("Novo Nome (Enter para manter): ");
        String n = input.nextLine();
        System.out.print("Novo Setor (Enter para manter): ");
        String s = input.nextLine();

        colabService.atualizar(id, new ColaboradorDTO(
                n.isEmpty() ? null : n,
                s.isEmpty() ? null : s,
                null
        ));
        System.out.println("Dados atualizados com sucesso!");
    }

    private void inativarColab() {
        System.out.print("ID do Colaborador para inativar: ");
        int id = input.nextInt();
        input.nextLine();
        colabService.inativar(id);
        System.out.println("Colaborador inativado.");
    }

    private void criarEpi() {
        System.out.println("\n--- NOVO EPI ---");
        System.out.print("Nome do EPI: ");
        String n = input.nextLine();
        System.out.print("Descricao: ");
        String d = input.nextLine();

        EpiDTO novo = epiService.criar(new EpiDTO(n, d, true));
        System.out.println("EPI cadastrado com sucesso!");
        System.out.println(novo);
    }

    private void listarEpi() {
        System.out.println("\n--- LISTA DE EPIs ---");
        epiService.listar().forEach(System.out::println);
    }

    private void editarEpi() {
        System.out.println("\n--- EDITAR EPI ---");
        System.out.print("ID do EPI: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Novo Nome (Enter para manter): ");
        String n = input.nextLine();
        System.out.print("Nova Descricao (Enter para manter): ");
        String d = input.nextLine(); // Corrigido para ler descricao

        epiService.atualizar(id, new EpiDTO(
                n.isEmpty() ? null : n,
                d.isEmpty() ? null : d,
                null
        ));
        System.out.println("EPI atualizado com sucesso!");
    }

    private void baixarEpi() {
        System.out.print("ID do EPI para dar baixa: ");
        int id = input.nextInt();
        input.nextLine();
        epiService.indisponibilizar(id);
        System.out.println("EPI marcado como Indisponivel.");
    }

    private void emprestar() {
        System.out.println("\n--- NOVO EMPRESTIMO ---");
        System.out.print("ID do Colaborador: ");
        int ic = input.nextInt();
        System.out.print("ID do EPI: ");
        int ie = input.nextInt();
        input.nextLine();

        System.out.print("Data de Devolucao (Ex: 2023-12-31) ou Enter: ");
        String d = input.nextLine();

        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setIdColaborador(ic);
        dto.setIdEpi(ie);

        if (!d.isEmpty()) {
            try {
                dto.setDataDevolucao(LocalDate.parse(d));
            } catch (Exception e) {
                System.out.println("Data invalida, registrando sem data prevista.");
            }
        }

        EmprestimoDTO salvo = empService.criar(dto);
        System.out.println("Emprestimo registrado com sucesso!");
        System.out.println(salvo);
    }

    private void devolver() {
        System.out.println("\n--- REGISTRAR DEVOLUCAO ---");
        System.out.print("ID do Emprestimo: ");
        int id = input.nextInt();
        input.nextLine();

        EmprestimoDTO devolvido = empService.devolver(id);
        System.out.println("EPI Devolvido e disponivel no estoque novamente.");
        System.out.println(devolvido);
    }

    private void listarAtivos() {
        System.out.println("\n--- EMPRESTIMOS ATIVOS ---");
        empService.listarAtivos().forEach(System.out::println);
    }
}