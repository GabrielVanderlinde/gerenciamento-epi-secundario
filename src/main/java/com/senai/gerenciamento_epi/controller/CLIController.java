package com.senai.gerenciamento_epi.controller;

import com.senai.gerenciamento_epi.dto.ColaboradorDTO;
import com.senai.gerenciamento_epi.dto.EpiDTO;
import com.senai.gerenciamento_epi.dto.EmprestimoDTO;
import com.senai.gerenciamento_epi.service.ColaboradorService;
import com.senai.gerenciamento_epi.service.EpiService;
import com.senai.gerenciamento_epi.service.EmprestimoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class CLIController implements CommandLineRunner {

    private final ColaboradorService colaboradorService;
    private final EpiService epiService;
    private final EmprestimoService emprestimoService;
    private final Scanner input = new Scanner(System.in);


    public CLIController(ColaboradorService colaboradorService,
                         EpiService epiService,
                         EmprestimoService emprestimoService) {
        this.colaboradorService = colaboradorService;
        this.epiService = epiService;
        this.emprestimoService = emprestimoService;
    }

    @Override
    public void run(String... args) {
        int opcao = 0;
        System.out.print("\n=== Gerenciamento de EPI - Console CLI ===\n");

        do {
            exibirMenu();
            try {
                opcao = input.nextInt();
                input.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1 -> criarColaborador();
                    case 2 -> buscarColaboradores();
                    case 3 -> atualizarColaborador();
                    case 4 -> inativarColaborador();

                    case 5 -> criarEpi();
                    case 6 -> buscarEpis();
                    case 7 -> atualizarEpi();
                    case 8 -> indisponibilizarEpi();

                    case 9 -> registrarEmprestimo();
                    case 10 -> registrarDevolucao();
                    case 11 -> buscarEmprestimosAtivos();

                    case 0 -> System.out.println("Salvando dados e saindo da aplicação...");
                    default -> System.out.println("Opção inválida. Tente novamente.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("=== 1. Colaboradores ===");
        System.out.println("1 - Cadastrar Colaborador");
        System.out.println("2 - Listar Colaboradores");
        System.out.println("3 - Atualizar Colaborador");
        System.out.println("4 - Inativar Colaborador (Exclusão Lógica)");

        System.out.println("\n=== 2. EPIs ===");
        System.out.println("5 - Cadastrar EPI");
        System.out.println("6 - Listar EPIs");
        System.out.println("7 - Atualizar EPI");
        System.out.println("8 - Indisponibilizar EPI (Exclusão Lógica)");

        System.out.println("\n=== 3. Empréstimos ===");
        System.out.println("9 - Registrar Empréstimo");
        System.out.println("10 - Registrar Devolução");
        System.out.println("11 - Listar Empréstimos Ativos");

        System.out.println("\n0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    // --- MÉTODOS DE COLABORADOR ---

    private void criarColaborador() {
        System.out.println("\n--- Cadastrar Colaborador ---");
        System.out.print("Informe o nome: "); String nome = input.nextLine();
        System.out.print("Informe o setor: "); String setor = input.nextLine();

        ColaboradorDTO novo = colaboradorService.criar(new ColaboradorDTO(nome, setor, "1"));
        System.out.println("Colaborador cadastrado com sucesso! ID: " + novo.getIdColaborador());
        System.out.println(novo);
    }

    private void buscarColaboradores() {
        System.out.println("\n--- Lista de Colaboradores ---");
        List<ColaboradorDTO> lista = colaboradorService.listar();
        if (lista.isEmpty()) System.out.println("Nenhum colaborador encontrado.");
        else lista.forEach(System.out::println);
    }

    private void atualizarColaborador() {
        System.out.println("\n--- Atualizar Colaborador ---");
        System.out.print("ID do colaborador: "); int id = input.nextInt(); input.nextLine();

        System.out.print("Novo nome (enter para manter): "); String nome = input.nextLine();
        System.out.print("Novo setor (enter para manter): "); String setor = input.nextLine();

        // Agora usamos o construtor manual do DTO
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setNomeColaborador(nome.isEmpty() ? null : nome);
        dto.setSetorColaborador(setor.isEmpty() ? null : setor);

        System.out.println(colaboradorService.atualizar(id, dto));
        System.out.println("Atualizado com sucesso!");
    }

    private void inativarColaborador() {
        System.out.print("ID do colaborador a inativar: "); int id = input.nextInt(); input.nextLine();
        colaboradorService.inativar(id);
        System.out.println("Colaborador inativado.");
    }

    // --- MÉTODOS DE EPI ---

    private void criarEpi() {
        System.out.println("\n--- Cadastrar EPI ---");
        System.out.print("Nome do EPI: "); String nome = input.nextLine();
        System.out.print("Descrição: "); String desc = input.nextLine();

        EpiDTO novo = epiService.criar(new EpiDTO(nome, desc, true));
        System.out.println("EPI cadastrado! ID: " + novo.getIdEpi());
        System.out.println(novo);
    }

    private void buscarEpis() {
        System.out.println("\n--- Lista de EPIs ---");
        List<EpiDTO> lista = epiService.listar();
        if (lista.isEmpty()) System.out.println("Nenhum EPI encontrado.");
        else lista.forEach(System.out::println);
    }

    private void atualizarEpi() {
        System.out.println("\n--- Atualizar EPI ---");
        System.out.print("ID do EPI: "); int id = input.nextInt(); input.nextLine();
        System.out.print("Novo nome (enter para manter): "); String nome = input.nextLine();
        System.out.print("Nova descrição (enter para manter): "); String desc = input.nextLine();

        EpiDTO dto = new EpiDTO();
        dto.setNomeEpi(nome.isEmpty() ? null : nome);
        dto.setDescricao(desc.isEmpty() ? null : desc);

        System.out.println(epiService.atualizar(id, dto));
        System.out.println("EPI atualizado!");
    }

    private void indisponibilizarEpi() {
        System.out.print("ID do EPI a indisponibilizar: "); int id = input.nextInt(); input.nextLine();
        epiService.indisponibilizar(id);
        System.out.println("EPI marcado como indisponível.");
    }

    // --- MÉTODOS DE EMPRÉSTIMO ---

    private void registrarEmprestimo() {
        System.out.println("\n--- Registrar Empréstimo ---");
        System.out.print("ID do Colaborador: "); int idC = input.nextInt();
        System.out.print("ID do EPI: "); int idE = input.nextInt(); input.nextLine();

        System.out.print("Data Devolução (AAAA-MM-DD) ou enter: "); String data = input.nextLine();

        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setIdColaborador(idC);
        dto.setIdEpi(idE);

        if (!data.isEmpty()) {
            try {
                dto.setDataDevolucao(LocalDate.parse(data));
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Registrando sem data prevista.");
            }
        }

        System.out.println(emprestimoService.criar(dto));
        System.out.println("Empréstimo realizado com sucesso!");
    }

    private void registrarDevolucao() {
        System.out.println("\n--- Registrar Devolução ---");
        System.out.print("ID do Empréstimo: "); int id = input.nextInt(); input.nextLine();
        System.out.println(emprestimoService.devolver(id));
        System.out.println("Devolução registrada e estoque liberado.");
    }

    private void buscarEmprestimosAtivos() {
        System.out.println("\n--- Empréstimos Ativos ---");
        List<EmprestimoDTO> lista = emprestimoService.listarAtivos();
        if (lista.isEmpty()) System.out.println("Nenhum empréstimo ativo no momento.");
        else lista.forEach(System.out::println);
    }
}