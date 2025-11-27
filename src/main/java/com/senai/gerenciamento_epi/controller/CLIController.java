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

    public CLIController(ColaboradorService c, EpiService e, EmprestimoService emp) {
        this.colabService = c; this.epiService = e; this.empService = emp;
    }

    @Override
    public void run(String... args) {
        int op = 0;
        do {
            System.out.println("\n=== SISTEMA DE EPI [v1.0] ===");
            System.out.println("1. Novo Colab | 2. Listar | 3. Editar | 4. Inativar");
            System.out.println("5. Novo EPI   | 6. Listar | 7. Editar | 8. Baixa");
            System.out.println("9. Emprestar  | 10. Devolver | 11. Ativos");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");
            try {
                op = input.nextInt(); input.nextLine();
                switch(op) {
                    case 1 -> {
                        System.out.print("Nome: "); String n = input.nextLine();
                        System.out.print("Setor: "); String s = input.nextLine();
                        System.out.println(colabService.criar(new ColaboradorDTO(n, s, "1")));
                    }
                    case 2 -> colabService.listar().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("ID: "); int id = input.nextInt(); input.nextLine();
                        System.out.print("Nome: "); String n = input.nextLine();
                        colabService.atualizar(id, new ColaboradorDTO(n.isEmpty()?null:n, null, null));
                    }
                    case 4 -> { System.out.print("ID: "); colabService.inativar(input.nextInt()); }
                    case 5 -> {
                        System.out.print("EPI: "); String n = input.nextLine();
                        System.out.print("Desc: "); String d = input.nextLine();
                        System.out.println(epiService.criar(new EpiDTO(n, d, true)));
                    }
                    case 6 -> epiService.listar().forEach(System.out::println);
                    case 7 -> {
                        System.out.print("ID: "); int id = input.nextInt(); input.nextLine();
                        System.out.print("Nome: "); String n = input.nextLine();
                        epiService.atualizar(id, new EpiDTO(n.isEmpty()?null:n, null, null));
                    }
                    case 8 -> { System.out.print("ID: "); epiService.indisponibilizar(input.nextInt()); }
                    case 9 -> {
                        System.out.print("ID Colab: "); int ic = input.nextInt();
                        System.out.print("ID EPI: "); int ie = input.nextInt(); input.nextLine();
                        System.out.print("Data Dev (AAAA-MM-DD): "); String d = input.nextLine();
                        EmprestimoDTO dto = new EmprestimoDTO(); dto.setIdColaborador(ic); dto.setIdEpi(ie);
                        if(!d.isEmpty()) dto.setDataDevolucao(LocalDate.parse(d));
                        System.out.println(empService.criar(dto));
                    }
                    case 10 -> { System.out.print("ID Emp: "); System.out.println(empService.devolver(input.nextInt())); }
                    case 11 -> empService.listarAtivos().forEach(System.out::println);
                    case 0 -> System.out.println("Saindo...");
                }
            } catch(Exception e) { System.out.println("Erro: " + e.getMessage()); input.nextLine(); }
        } while(op != 0);
    }
}