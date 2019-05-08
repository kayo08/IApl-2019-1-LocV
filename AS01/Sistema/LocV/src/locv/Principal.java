/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locv;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Murilo
 */
public class Principal extends javax.swing.JFrame {

    ArrayList<Cliente> ClientList;
    ArrayList<Veiculo> VehicleList;
    ArrayList<Locacao> LocationList;
    String modo;

    public void LoadTableClient() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Nome", "CPF", "RG", "Nacionalidade", "Telefone", "Nascimento", "Sexo"}, 0);
        for (int i = 0; i < ClientList.size(); i++) {
            Object linha[] = new Object[]{ClientList.get(i).getNome(), ClientList.get(i).getCpf(), ClientList.get(i).getRg(),
                ClientList.get(i).getNacionalidade(), ClientList.get(i).getTelefone(), ClientList.get(i).getNascimento(), ClientList.get(i).getSexo()
            };
            modelo.addRow(linha);
        }
        table_cliente_clientes.setModel(modelo);
    }

    public void LoadTableLocation() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"CPF Locação","Placa Locação","Data Locação", "Hora Locação", "Data Devolução", "Hora Devolução", "Número Locação"}, 0);
        for (int i = 0; i < LocationList.size(); i++) {
            Object linha[] = new Object[]{LocationList.get(i).getCpfLocacao(),LocationList.get(i).getPlacaLocacao(),LocationList.get(i).getDataLocacao(), LocationList.get(i).getHorarioLocacao(), LocationList.get(i).getDataDevolucao(),
                LocationList.get(i).getHorarioDevolucao(), LocationList.get(i).getNumeroLocacao()
            };
            modelo.addRow(linha);
        }
        table_locacao_locacoes.setModel(modelo);
    }

    public void LoadTableVehicle() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Marca", "Modelo", "Placa", "Cor", "Ano"}, 0);
        for (int i = 0; i < VehicleList.size(); i++) {
            Object linha[] = new Object[]{VehicleList.get(i).getMarca(), VehicleList.get(i).getModelo(), VehicleList.get(i).getPlaca(), VehicleList.get(i).getCor(),
                VehicleList.get(i).getAno()
            };
            modelo.addRow(linha);
        }
        table_veiculo_veiculos.setModel(modelo);
    }

    /**
     * Creates new form Principal
     */
    public Principal() throws IOException {
        initComponents();
        setLocationRelativeTo(null);
        ClientList = new ArrayList();
        VehicleList = new ArrayList();
        LocationList = new ArrayList();
        modo = "Navegar";
        manipulaInterfaceCliente();
        manipulaInterfaceVeiculo();
        manipulaInterfaceLocacao();
        carregarInformacaoCliente();
        carregarInformacaoVeiculo();
        carregarInformacaoLocacao();
        LoadTableClient();
        LoadTableVehicle();
        LoadTableLocation();
        fecharAplicacao();
        apagarInformacaoCliente();
        apagarInformacaoVeiculo();
        apagarInformacaoLocacao();
        iniciarCampos();

    }

    public void manipulaInterfaceLocacao() {
        switch (modo) {
            case "Navegar":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            case "Novo":
                botao_locacao_salvar.setEnabled(true);
                botao_locacao_cancelar.setEnabled(true);
                botao_locacao_novo.setEnabled(false);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(true);
                campo_locacao_horaloc.setEnabled(true);
                campo_locacao_datadev.setEnabled(true);
                campo_locacao_horadev.setEnabled(true);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(true);
                campo_locacao_placa.setEnabled(true);
                break;
            case "Editar":
                botao_locacao_salvar.setEnabled(true);
                botao_locacao_cancelar.setEnabled(true);
                botao_locacao_novo.setEnabled(false);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(true);
                campo_locacao_horaloc.setEnabled(true);
                campo_locacao_datadev.setEnabled(true);
                campo_locacao_horadev.setEnabled(true);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(true);
                campo_locacao_placa.setEnabled(true);
                break;
            case "Excluir":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(false);
                botao_locacao_excluir.setEnabled(false);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            case "Selecao":
                botao_locacao_salvar.setEnabled(false);
                botao_locacao_cancelar.setEnabled(false);
                botao_locacao_novo.setEnabled(true);
                botao_locacao_editar.setEnabled(true);
                botao_locacao_excluir.setEnabled(true);
                campo_locacao_dataloc.setEnabled(false);
                campo_locacao_horaloc.setEnabled(false);
                campo_locacao_datadev.setEnabled(false);
                campo_locacao_horadev.setEnabled(false);
                campo_locacao_numloc.setEnabled(false);
                campo_locacao_cpf.setEnabled(false);
                campo_locacao_placa.setEnabled(false);
                break;
            default:
                System.out.println("Modo inválido");
        }
    }

    public void manipulaInterfaceVeiculo() {
        switch (modo) {
            case "Navegar":
                botao_veiculo_salvar.setEnabled(false);
                botao_veiculo_cancelar.setEnabled(false);
                botao_veiculo_novo.setEnabled(true);
                botao_veiculo_editar.setEnabled(false);
                botao_veiculo_excluir.setEnabled(false);
                campo_veiculo_marca.setEnabled(false);
                campo_veiculo_modelo.setEnabled(false);
                campo_veiculo_placa.setEnabled(false);
                campo_veiculo_cor.setEnabled(false);
                campo_veiculo_ano.setEnabled(false);
                break;
            case "Novo":
                botao_veiculo_salvar.setEnabled(true);
                botao_veiculo_cancelar.setEnabled(true);
                botao_veiculo_novo.setEnabled(false);
                botao_veiculo_editar.setEnabled(false);
                botao_veiculo_excluir.setEnabled(false);
                campo_veiculo_marca.setEnabled(true);
                campo_veiculo_modelo.setEnabled(true);
                campo_veiculo_placa.setEnabled(true);
                campo_veiculo_cor.setEnabled(true);
                campo_veiculo_ano.setEnabled(true);
                break;
            case "Editar":
                botao_veiculo_salvar.setEnabled(true);
                botao_veiculo_cancelar.setEnabled(true);
                botao_veiculo_novo.setEnabled(false);
                botao_veiculo_editar.setEnabled(false);
                botao_veiculo_excluir.setEnabled(false);
                campo_veiculo_marca.setEnabled(true);
                campo_veiculo_modelo.setEnabled(true);
                campo_veiculo_placa.setEnabled(true);
                campo_veiculo_cor.setEnabled(true);
                campo_veiculo_ano.setEnabled(true);
                break;
            case "Excluir":
                botao_veiculo_salvar.setEnabled(false);
                botao_veiculo_cancelar.setEnabled(false);
                botao_veiculo_novo.setEnabled(true);
                botao_veiculo_editar.setEnabled(false);
                botao_veiculo_excluir.setEnabled(false);
                campo_veiculo_marca.setEnabled(false);
                campo_veiculo_modelo.setEnabled(false);
                campo_veiculo_placa.setEnabled(false);
                campo_veiculo_cor.setEnabled(false);
                campo_veiculo_ano.setEnabled(false);
                break;
            case "Selecao":
                botao_veiculo_salvar.setEnabled(false);
                botao_veiculo_cancelar.setEnabled(false);
                botao_veiculo_novo.setEnabled(true);
                botao_veiculo_editar.setEnabled(true);
                botao_veiculo_excluir.setEnabled(true);
                campo_veiculo_marca.setEnabled(false);
                campo_veiculo_modelo.setEnabled(false);
                campo_veiculo_placa.setEnabled(false);
                campo_veiculo_cor.setEnabled(false);
                campo_veiculo_ano.setEnabled(false);
                break;
            default:
                System.out.println("Modo inválido");
        }
    }

    public void manipulaInterfaceCliente() {
        switch (modo) {
            case "Navegar":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Novo":
                botao_cliente_salvar.setEnabled(true);
                botao_cliente_cancelar.setEnabled(true);
                campo_cliente_nome.setEnabled(true);
                campo_cliente_cpf.setEnabled(true);
                campo_cliente_rg.setEnabled(true);
                campo_cliente_nacionalidade.setEnabled(true);
                campo_cliente_telefone.setEnabled(true);
                campo_cliente_nascimento.setEnabled(true);
                campo_cliente_sexo.setEnabled(true);
                botao_cliente_novo.setEnabled(false);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Editar":
                botao_cliente_salvar.setEnabled(true);
                botao_cliente_cancelar.setEnabled(true);
                campo_cliente_nome.setEnabled(true);
                campo_cliente_cpf.setEnabled(true);
                campo_cliente_rg.setEnabled(true);
                campo_cliente_nacionalidade.setEnabled(true);
                campo_cliente_telefone.setEnabled(true);
                campo_cliente_nascimento.setEnabled(true);
                campo_cliente_sexo.setEnabled(true);
                botao_cliente_novo.setEnabled(false);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Excluir":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(false);
                botao_cliente_excluir.setEnabled(false);
                break;
            case "Selecao":
                botao_cliente_salvar.setEnabled(false);
                botao_cliente_cancelar.setEnabled(false);
                campo_cliente_nome.setEnabled(false);
                campo_cliente_cpf.setEnabled(false);
                campo_cliente_rg.setEnabled(false);
                campo_cliente_nacionalidade.setEnabled(false);
                campo_cliente_telefone.setEnabled(false);
                campo_cliente_nascimento.setEnabled(false);
                campo_cliente_sexo.setEnabled(false);
                botao_cliente_novo.setEnabled(true);
                botao_cliente_editar.setEnabled(true);
                botao_cliente_excluir.setEnabled(true);
                break;
            default:
                System.out.println("Modo inválido");
        }
    }

    public void limparCamposCliente() {
        campo_cliente_nome.setText("");
        campo_cliente_cpf.setText("");
        campo_cliente_rg.setText("");
        campo_cliente_nacionalidade.setText("");
        campo_cliente_telefone.setText("");
        campo_cliente_nascimento.setText("");
        campo_cliente_sexo.setText("");
    }

    public void limparCamposVeiculo() {
        campo_veiculo_marca.setText("");
        campo_veiculo_modelo.setText("");
        campo_veiculo_placa.setText("");
        campo_veiculo_cor.setText("");
        campo_veiculo_ano.setText("");
    }

    public void limparCamposLocacao() {
        campo_locacao_dataloc.setText("");
        campo_locacao_horaloc.setText("");
        campo_locacao_datadev.setText("");
        campo_locacao_horadev.setText("");
        campo_locacao_numloc.setText("");
        campo_locacao_cpf.setText("");
        campo_locacao_placa.setText("");
    }

    public void iniciarCampos() {
        campo_cliente_nome.setText("");
        campo_cliente_cpf.setText("");
        campo_cliente_rg.setText("");
        campo_cliente_nacionalidade.setText("");
        campo_cliente_telefone.setText("");
        campo_cliente_nascimento.setText("");
        campo_cliente_sexo.setText("");
        campo_veiculo_marca.setText("");
        campo_veiculo_modelo.setText("");
        campo_veiculo_placa.setText("");
        campo_veiculo_cor.setText("");
        campo_veiculo_ano.setText("");
        campo_locacao_dataloc.setText("");
        campo_locacao_horaloc.setText("");
        campo_locacao_datadev.setText("");
        campo_locacao_horadev.setText("");
        campo_locacao_numloc.setText("");
        campo_locacao_cpf.setText("");
        campo_locacao_placa.setText("");
    }

    public void gravarInformacaoVeiculo() {
        File file = new File("Veiculo.txt");
        String marca, modelo, placa, cor, ano = ""; // só um exemplo, crie todoas as variaveis que são atributos dos objetos do array list
        String conteudo;
        String espacosm = "";
        String espacosmo = "";
        String espacosp = "";
        String espacosc = "";
        String espacosa = "";
        try {
            FileWriter f = new FileWriter(file, true);
            for (int i = 0; i < VehicleList.size(); i++) {
                //Marca
                marca = VehicleList.get(i).getMarca();
                int tamanho_m = 100 - marca.length();
                for (int ii = 0; ii < tamanho_m; ii++){
                    espacosm+=" ";
                }
                marca+=espacosm;
                //Modelo
                modelo = VehicleList.get(i).getModelo();
                int tamanho_mo = 100 - modelo.length();
                for (int ii = 0; ii < tamanho_mo; ii++ ){
                    espacosmo+=" ";
                }
                modelo+=espacosmo;
                //Placa
                placa = VehicleList.get(i).getPlaca();
                int tamanho_p = 12 - placa.length();
                for (int ii = 0; ii < tamanho_p; ii++){
                    espacosp+=" ";
                }
                placa+=espacosp;
                //Cor
                cor = VehicleList.get(i).getCor();
                int tamanho_c = 30 - cor.length();
                for (int ii=0; ii < tamanho_c; ii++){
                    espacosc+=" ";
                }
                cor+=espacosp;
                //Ano
                ano = String.valueOf(VehicleList.get(i).getAno());
                int tamanho_ano = 4 - ano.length();
                for (int ii = 0; ii < tamanho_ano; ii++){
                    espacosa+=" ";
                }
                ano+=espacosa;
                
                conteudo = marca + modelo + placa + cor + ano;
                conteudo += "\r\n";
                f.write(conteudo);
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gravarInformacaoLocacao() {
        File file = new File("Locacao.txt");
        String cpfLocacao, placaLocacao,dataLocacao, horaLocacao, dataDevolucao, horaDevolucao, numLocacao = ""; // só um exemplo, crie todoas as variaveis que são atributos dos objetos do array list
        String conteudo;
        try {
            FileWriter f = new FileWriter(file, true);
            for (int i = 0; i < LocationList.size(); i++) {
                cpfLocacao = LocationList.get(i).getCpfLocacao();
                placaLocacao = LocationList.get(i).getPlacaLocacao();
                dataLocacao = LocationList.get(i).getDataLocacao();
                horaLocacao = LocationList.get(i).getHorarioLocacao();
                dataDevolucao = LocationList.get(i).getDataDevolucao();
                horaDevolucao = LocationList.get(i).getHorarioDevolucao();
                numLocacao = String.valueOf(LocationList.get(i).getNumeroLocacao());
                conteudo = cpfLocacao+","+placaLocacao+","+dataLocacao + "," + horaLocacao + "," + dataDevolucao + "," + horaDevolucao + "," + numLocacao;
                conteudo += "\r\n";
                f.write(conteudo);
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gravarInformacaoCliente() {
        File file = new File("Cliente.txt");
        String nome, cpf, rg, nacionalidade, telefone, nascimento, sexo = ""; // só um exemplo, crie todoas as variaveis que são atributos dos objetos do array list
        String conteudo;
        try {
            FileWriter f = new FileWriter(file, true);
            for (int i = 0; i < ClientList.size(); i++) {
                nome = ClientList.get(i).getNome();
                cpf = ClientList.get(i).getCpf();
                rg = ClientList.get(i).getRg();
                nacionalidade = ClientList.get(i).getNacionalidade();
                telefone = ClientList.get(i).getTelefone();
                nascimento = ClientList.get(i).getNascimento();
                sexo = ClientList.get(i).getSexo();
                conteudo = nome + "," + cpf + "," + rg + "," + nacionalidade + "," + telefone + "," + nascimento + "," + sexo;
                conteudo += "\r\n";
                f.write(conteudo);
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarInformacaoVeiculo() {
        String marca, modelo, placa, cor, anotemp = "";
        int ano;
        try {

            FileReader fr = new FileReader("Veiculo.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                marca = (str.split(",")[0]);
                modelo = (str.split(",")[1]);
                placa = (str.split(",")[2]);
                cor = (str.split(",")[3]);
                anotemp = (str.split(",")[4]);
                ano = Integer.parseInt(anotemp);
                Veiculo D = new Veiculo(marca, modelo, placa, cor, ano);
                VehicleList.add(D);
            }
            br.close();
        } catch (IOException e) {
            out.println("");
        }
    }

    public void carregarInformacaoLocacao() {
        String cpfLocacao, placaLocacao,dataLocacao, horaLocacao, dataDevolucao, horaDevolucao, numLocacaoTemp = "";
        int numLocacao;
        try {

            FileReader fr = new FileReader("Locacao.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                cpfLocacao = (str.split(",")[0]);
                placaLocacao = (str.split(",")[1]);
                dataLocacao = (str.split(",")[2]);
                horaLocacao = (str.split(",")[3]);
                dataDevolucao = (str.split(",")[4]);
                horaDevolucao = (str.split(",")[5]);
                numLocacaoTemp = (str.split(",")[6]);
                numLocacao = Integer.parseInt(numLocacaoTemp);
                Locacao D = new Locacao(cpfLocacao,placaLocacao,dataLocacao, horaLocacao, dataDevolucao, horaDevolucao, numLocacao);
                LocationList.add(D);
            }
            br.close();
        } catch (IOException e) {
            out.println("");
        }
    }

    public void carregarInformacaoCliente() {
        String nome, cpf, rg, nacionalidade, telefone, nascimento, sexo = "";
        try {

            FileReader fr = new FileReader("Cliente.txt");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                nome = (str.split(",")[0]);
                cpf = (str.split(",")[1]);
                rg = (str.split(",")[2]);
                nacionalidade = (str.split(",")[3]);
                telefone = (str.split(",")[4]);
                nascimento = (str.split(",")[5]);
                sexo = (str.split(",")[6]);
                Cliente D = new Cliente(nome, cpf, rg, nacionalidade, telefone, nascimento, sexo);
                ClientList.add(D);
            }
            br.close();
        } catch (IOException e) {
            out.println("");
        }
    }

    public void apagarInformacaoVeiculo() throws IOException {
        Writer out = new FileWriter("Veiculo.txt");
        //limpa
        out.write("");
        out.flush();
        out.close();
    }

    public void apagarInformacaoCliente() throws IOException {
        Writer out = new FileWriter("Cliente.txt");
        //limpa
        out.write("");
        out.flush();
        out.close();
    }

    public void apagarInformacaoLocacao() throws IOException {
        Writer out = new FileWriter("Locacao.txt");
        //limpa
        out.write("");
        out.flush();
        out.close();
    }

    public void fecharAplicacao() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                /*if (JOptionPane.showConfirmDialog(null, "Deseja sair") == JOptionPane.OK_OPTION) {
                 gravarInformacaoCliente();
                 gravarInformacaoVeiculo();
                 gravarInformacaoLocacao();
                 }*/
                gravarInformacaoCliente();
                gravarInformacaoVeiculo();
                gravarInformacaoLocacao();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_cliente_clientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campo_cliente_nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campo_cliente_cpf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campo_cliente_rg = new javax.swing.JTextField();
        campo_cliente_nacionalidade = new javax.swing.JTextField();
        campo_cliente_telefone = new javax.swing.JTextField();
        campo_cliente_nascimento = new javax.swing.JTextField();
        campo_cliente_sexo = new javax.swing.JTextField();
        botao_cliente_salvar = new javax.swing.JButton();
        botao_cliente_cancelar = new javax.swing.JButton();
        botao_cliente_novo = new javax.swing.JButton();
        botao_cliente_editar = new javax.swing.JButton();
        botao_cliente_excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_veiculo_veiculos = new javax.swing.JTable();
        botao_veiculo_novo = new javax.swing.JButton();
        botao_veiculo_editar = new javax.swing.JButton();
        botao_veiculo_excluir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        campo_veiculo_marca = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campo_veiculo_modelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campo_veiculo_placa = new javax.swing.JTextField();
        campo_veiculo_cor = new javax.swing.JTextField();
        campo_veiculo_ano = new javax.swing.JTextField();
        botao_veiculo_salvar = new javax.swing.JButton();
        botao_veiculo_cancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_locacao_locacoes = new javax.swing.JTable();
        botao_locacao_novo = new javax.swing.JButton();
        botao_locacao_editar = new javax.swing.JButton();
        botao_locacao_excluir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        campo_locacao_dataloc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        campo_locacao_horaloc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        campo_locacao_datadev = new javax.swing.JTextField();
        campo_locacao_horadev = new javax.swing.JTextField();
        campo_locacao_numloc = new javax.swing.JTextField();
        botao_locacao_salvar = new javax.swing.JButton();
        botao_locacao_cancelar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        campo_locacao_cpf = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        campo_locacao_placa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        table_cliente_clientes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_cliente_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cpf", "Rg", "Nacionalidade", "Telefone", "Nascimento", "Sexo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_cliente_clientes);
        if (table_cliente_clientes.getColumnModel().getColumnCount() > 0) {
            table_cliente_clientes.getColumnModel().getColumn(5).setHeaderValue("Nascimento");
            table_cliente_clientes.getColumnModel().getColumn(6).setHeaderValue("Sexo");
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(750, 260));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Nome:");

        campo_cliente_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nomeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("CPF:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("RG:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nacionalidade:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Telefone:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Nascimento:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Sexo:");

        campo_cliente_rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_rgActionPerformed(evt);
            }
        });

        campo_cliente_nacionalidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nacionalidadeActionPerformed(evt);
            }
        });

        campo_cliente_nascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cliente_nascimentoActionPerformed(evt);
            }
        });

        botao_cliente_salvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_salvar.setText("Salvar");
        botao_cliente_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_salvarActionPerformed(evt);
            }
        });

        botao_cliente_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_cancelar.setText("Cancelar");
        botao_cliente_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(campo_cliente_sexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(campo_cliente_nascimento, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_cliente_telefone, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campo_cliente_nacionalidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(campo_cliente_rg))
                                .addGap(93, 93, 93)))
                        .addComponent(botao_cliente_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(botao_cliente_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campo_cliente_cpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(campo_cliente_nome, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(campo_cliente_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_cliente_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(campo_cliente_rg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(campo_cliente_nacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(campo_cliente_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(campo_cliente_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(campo_cliente_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_cliente_cancelar)
                            .addComponent(botao_cliente_salvar))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        botao_cliente_novo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_novo.setText("Novo");
        botao_cliente_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_novoActionPerformed(evt);
            }
        });

        botao_cliente_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_editar.setText("Editar");
        botao_cliente_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_editarActionPerformed(evt);
            }
        });

        botao_cliente_excluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_cliente_excluir.setText("Excluir");
        botao_cliente_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_cliente_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(botao_cliente_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_cliente_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(botao_cliente_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botao_cliente_excluir)
                    .addComponent(botao_cliente_novo)
                    .addComponent(botao_cliente_editar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel1);

        table_veiculo_veiculos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_veiculo_veiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Marca", "Modelo", "Placa", "Cor", "Ano"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_veiculo_veiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_veiculo_veiculosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_veiculo_veiculos);

        botao_veiculo_novo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_veiculo_novo.setText("Novo");
        botao_veiculo_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_veiculo_novoActionPerformed(evt);
            }
        });

        botao_veiculo_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_veiculo_editar.setText("Editar");
        botao_veiculo_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_veiculo_editarActionPerformed(evt);
            }
        });

        botao_veiculo_excluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_veiculo_excluir.setText("Excluir");
        botao_veiculo_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_veiculo_excluirActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Veículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(750, 260));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Marca:");

        campo_veiculo_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_veiculo_marcaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Modelo:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Placa:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Cor:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Ano:");

        campo_veiculo_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_veiculo_placaActionPerformed(evt);
            }
        });

        campo_veiculo_cor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_veiculo_corActionPerformed(evt);
            }
        });

        botao_veiculo_salvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_veiculo_salvar.setText("Salvar");
        botao_veiculo_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_veiculo_salvarActionPerformed(evt);
            }
        });

        botao_veiculo_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_veiculo_cancelar.setText("Cancelar");
        botao_veiculo_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_veiculo_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_veiculo_ano, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_veiculo_placa)
                    .addComponent(campo_veiculo_modelo)
                    .addComponent(campo_veiculo_marca)
                    .addComponent(campo_veiculo_cor))
                .addGap(53, 53, 53)
                .addComponent(botao_veiculo_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(botao_veiculo_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_veiculo_cancelar)
                            .addComponent(botao_veiculo_salvar)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(campo_veiculo_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_veiculo_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(campo_veiculo_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(campo_veiculo_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(campo_veiculo_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(botao_veiculo_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(botao_veiculo_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(botao_veiculo_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addComponent(jScrollPane2)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botao_veiculo_novo)
                    .addComponent(botao_veiculo_editar)
                    .addComponent(botao_veiculo_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Veículos", jPanel2);

        table_locacao_locacoes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_locacao_locacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data Locação", "Hora Locação", "Data Devolução", "Hora Devolução", "Número Locação", "Cpf Cliente", "Placa Veículo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_locacao_locacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_locacao_locacoesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_locacao_locacoes);

        botao_locacao_novo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_novo.setText("Novo");
        botao_locacao_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_novoActionPerformed(evt);
            }
        });

        botao_locacao_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_editar.setText("Editar");
        botao_locacao_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_editarActionPerformed(evt);
            }
        });

        botao_locacao_excluir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_excluir.setText("Excluir");
        botao_locacao_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_excluirActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(750, 260));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Data Locação:");

        campo_locacao_dataloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_datalocActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Hora Locação:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Data Devolução:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Hora Devolução:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Numero Locação:");

        campo_locacao_datadev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_datadevActionPerformed(evt);
            }
        });

        campo_locacao_horadev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_locacao_horadevActionPerformed(evt);
            }
        });

        botao_locacao_salvar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_salvar.setText("Salvar");
        botao_locacao_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_salvarActionPerformed(evt);
            }
        });

        botao_locacao_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botao_locacao_cancelar.setText("Cancelar");
        botao_locacao_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_locacao_cancelarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("CPF Locação:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Placa Locação:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_locacao_horadev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(campo_locacao_datadev, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_locacao_numloc, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_locacao_horaloc)
                    .addComponent(campo_locacao_dataloc)
                    .addComponent(campo_locacao_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campo_locacao_placa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(53, 53, 53)
                .addComponent(botao_locacao_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(botao_locacao_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botao_locacao_cancelar)
                            .addComponent(botao_locacao_salvar)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(campo_locacao_dataloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_locacao_horaloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(campo_locacao_datadev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(campo_locacao_horadev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(campo_locacao_numloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(campo_locacao_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(campo_locacao_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(botao_locacao_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao_locacao_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190)
                .addComponent(botao_locacao_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addComponent(jScrollPane3)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botao_locacao_novo)
                    .addComponent(botao_locacao_editar)
                    .addComponent(botao_locacao_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Locação", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_cliente_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nomeActionPerformed

    private void campo_cliente_rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_rgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_rgActionPerformed

    private void campo_cliente_nacionalidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nacionalidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nacionalidadeActionPerformed
    private void botao_cliente_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_novoActionPerformed
        limparCamposCliente();
        modo = "Novo";
        manipulaInterfaceCliente();
    }//GEN-LAST:event_botao_cliente_novoActionPerformed

    private void campo_cliente_nascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cliente_nascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cliente_nascimentoActionPerformed

    private void botao_cliente_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_cancelarActionPerformed
        limparCamposCliente();
        modo = "Navegar";
        manipulaInterfaceCliente();
    }//GEN-LAST:event_botao_cliente_cancelarActionPerformed

    private void botao_cliente_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_salvarActionPerformed

        if (modo.equals("Novo")) {
            Cliente D = new Cliente(campo_cliente_nome.getText(), campo_cliente_cpf.getText(), campo_cliente_rg.getText(),
                    campo_cliente_nacionalidade.getText(), campo_cliente_telefone.getText(), campo_cliente_nascimento.getText(),
                    campo_cliente_sexo.getText());
            ClientList.add(D);
        } else if (modo.equals("Editar")) {
            int index = table_cliente_clientes.getSelectedRow();
            ClientList.get(index).setNome(campo_cliente_nome.getText());
            ClientList.get(index).setCpf(campo_cliente_cpf.getText());
            ClientList.get(index).setRg(campo_cliente_rg.getText());
            ClientList.get(index).setNacionalidade(campo_cliente_nacionalidade.getText());
            ClientList.get(index).setTelefone(campo_cliente_telefone.getText());
            ClientList.get(index).setNascimento(campo_cliente_nascimento.getText());
            ClientList.get(index).setSexo(campo_cliente_sexo.getText());

        }

        LoadTableClient();
        modo = "Navegar";
        manipulaInterfaceCliente();
        limparCamposCliente();
    }//GEN-LAST:event_botao_cliente_salvarActionPerformed

    private void table_cliente_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_clientesMouseClicked
        int index = table_cliente_clientes.getSelectedRow();
        if (index >= 0 && index < ClientList.size()) {
            Cliente D = ClientList.get(index);
            campo_cliente_nome.setText(D.getNome());
            campo_cliente_cpf.setText(D.getCpf());
            campo_cliente_rg.setText(D.getRg());
            campo_cliente_nacionalidade.setText(D.getNacionalidade());
            campo_cliente_telefone.setText(D.getTelefone());
            campo_cliente_nascimento.setText(D.getNascimento());
            campo_cliente_sexo.setText(D.getSexo());
            modo = "Selecao";
            manipulaInterfaceCliente();
        }
    }//GEN-LAST:event_table_cliente_clientesMouseClicked

    private void botao_cliente_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_editarActionPerformed
        modo = "Editar";
        manipulaInterfaceCliente();
    }//GEN-LAST:event_botao_cliente_editarActionPerformed

    private void botao_cliente_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_cliente_excluirActionPerformed
        int index = table_cliente_clientes.getSelectedRow();
        if (index >= 0 && index < ClientList.size()) {
            ClientList.remove(index);
        }
        LoadTableClient();
        modo = "Navegar";
        manipulaInterfaceCliente();
        limparCamposCliente();
    }//GEN-LAST:event_botao_cliente_excluirActionPerformed

    private void table_veiculo_veiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_veiculo_veiculosMouseClicked
        int index = table_veiculo_veiculos.getSelectedRow();
        if (index >= 0 && index < VehicleList.size()) {
            Veiculo D = VehicleList.get(index);
            campo_veiculo_marca.setText(D.getMarca());
            campo_veiculo_modelo.setText(D.getModelo());
            campo_veiculo_placa.setText(D.getPlaca());
            campo_veiculo_cor.setText(D.getCor());
            campo_veiculo_ano.setText(String.valueOf(D.getAno()));
            modo = "Selecao";
            manipulaInterfaceVeiculo();
        }
    }//GEN-LAST:event_table_veiculo_veiculosMouseClicked

    private void botao_veiculo_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_veiculo_novoActionPerformed
        limparCamposVeiculo();
        modo = "Novo";
        manipulaInterfaceVeiculo();
    }//GEN-LAST:event_botao_veiculo_novoActionPerformed

    private void botao_veiculo_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_veiculo_editarActionPerformed
        modo = "Editar";
        manipulaInterfaceVeiculo();
    }//GEN-LAST:event_botao_veiculo_editarActionPerformed

    private void botao_veiculo_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_veiculo_excluirActionPerformed
        int index = table_veiculo_veiculos.getSelectedRow();
        if (index >= 0 && index < VehicleList.size()) {
            VehicleList.remove(index);
        }
        LoadTableVehicle();
        modo = "Navegar";
        manipulaInterfaceVeiculo();
        limparCamposVeiculo();
    }//GEN-LAST:event_botao_veiculo_excluirActionPerformed

    private void campo_veiculo_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_veiculo_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_veiculo_marcaActionPerformed

    private void campo_veiculo_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_veiculo_placaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_veiculo_placaActionPerformed

    private void campo_veiculo_corActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_veiculo_corActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_veiculo_corActionPerformed

    private void botao_veiculo_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_veiculo_salvarActionPerformed
        int ano = Integer.parseInt(campo_veiculo_ano.getText());
        if (modo.equals("Novo")) {

            Veiculo D = new Veiculo(campo_veiculo_marca.getText(), campo_veiculo_modelo.getText(), campo_veiculo_placa.getText(), campo_veiculo_cor.getText(),
                    ano);
            VehicleList.add(D);
        } else if (modo.equals("Editar")) {
            int index = table_veiculo_veiculos.getSelectedRow();
            VehicleList.get(index).setMarca(campo_veiculo_marca.getText());
            VehicleList.get(index).setModelo(campo_veiculo_modelo.getText());
            VehicleList.get(index).setPlaca(campo_veiculo_placa.getText());
            VehicleList.get(index).setCor(campo_veiculo_cor.getText());
            VehicleList.get(index).setAno(ano);

        }

        LoadTableVehicle();
        modo = "Navegar";
        manipulaInterfaceVeiculo();
        limparCamposVeiculo();
    }//GEN-LAST:event_botao_veiculo_salvarActionPerformed

    private void botao_veiculo_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_veiculo_cancelarActionPerformed
        limparCamposVeiculo();
        modo = "Navegar";
        manipulaInterfaceVeiculo();
    }//GEN-LAST:event_botao_veiculo_cancelarActionPerformed

    private void table_locacao_locacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_locacao_locacoesMouseClicked
        int index = table_locacao_locacoes.getSelectedRow();
        if (index >= 0 && index < LocationList.size()) {
            Locacao D = LocationList.get(index);
            campo_locacao_cpf.setText(D.getCpfLocacao());
            campo_locacao_placa.setText(D.getPlacaLocacao());
            campo_locacao_dataloc.setText(D.getDataLocacao());
            campo_locacao_horaloc.setText(D.getHorarioLocacao());
            campo_locacao_datadev.setText(D.getDataDevolucao());
            campo_locacao_horadev.setText(D.getHorarioDevolucao());
            campo_locacao_numloc.setText(String.valueOf(D.getNumeroLocacao()));
            modo = "Selecao";
            manipulaInterfaceLocacao();
        }
    }//GEN-LAST:event_table_locacao_locacoesMouseClicked

    private void botao_locacao_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_novoActionPerformed
        limparCamposLocacao();
        campo_locacao_numloc.setText(String.valueOf(LocationList.size()));
        modo = "Novo";
        manipulaInterfaceLocacao();
    }//GEN-LAST:event_botao_locacao_novoActionPerformed

    private void botao_locacao_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_editarActionPerformed
        modo = "Editar";
        manipulaInterfaceLocacao();
    }//GEN-LAST:event_botao_locacao_editarActionPerformed

    private void botao_locacao_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_excluirActionPerformed
        int index = table_locacao_locacoes.getSelectedRow();
        if (index >= 0 && index < LocationList.size()) {
            LocationList.remove(index);
        }
        LoadTableLocation();
        modo = "Navegar";
        manipulaInterfaceLocacao();
        limparCamposLocacao();
    }//GEN-LAST:event_botao_locacao_excluirActionPerformed

    private void campo_locacao_datalocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_datalocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_datalocActionPerformed

    private void campo_locacao_datadevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_datadevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_datadevActionPerformed

    private void campo_locacao_horadevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_locacao_horadevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_locacao_horadevActionPerformed

    private void botao_locacao_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_salvarActionPerformed
        int numLocacao = (LocationList.size());
        if (modo.equals("Novo")) {

            Locacao D = new Locacao(campo_locacao_cpf.getText(),campo_locacao_placa.getText(),campo_locacao_dataloc.getText(), campo_locacao_horaloc.getText(), campo_locacao_datadev.getText(), campo_locacao_horadev.getText(),
                    numLocacao);
            LocationList.add(D);
        } else if (modo.equals("Editar")) {
            int index = table_locacao_locacoes.getSelectedRow();
            LocationList.get(index).setCpfLocacao(campo_locacao_cpf.getText());
            LocationList.get(index).setPlacaLocacao(campo_locacao_placa.getText());
            LocationList.get(index).setDataLocacao(campo_locacao_dataloc.getText());
            LocationList.get(index).setHorarioLocacao(campo_locacao_horaloc.getText());
            LocationList.get(index).setDataDevolucao(campo_locacao_datadev.getText());
            LocationList.get(index).setHorarioDevolucao(campo_locacao_horadev.getText());
            LocationList.get(index).setNumeroLocacao(numLocacao);
        }

        LoadTableLocation();
        modo = "Navegar";
        manipulaInterfaceLocacao();
        limparCamposLocacao();
    }//GEN-LAST:event_botao_locacao_salvarActionPerformed

    private void botao_locacao_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botao_locacao_cancelarActionPerformed
        limparCamposLocacao();
        modo = "Navegar";
        manipulaInterfaceLocacao();
    }//GEN-LAST:event_botao_locacao_cancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botao_cliente_cancelar;
    private javax.swing.JButton botao_cliente_editar;
    private javax.swing.JButton botao_cliente_excluir;
    private javax.swing.JButton botao_cliente_novo;
    private javax.swing.JButton botao_cliente_salvar;
    private javax.swing.JButton botao_locacao_cancelar;
    private javax.swing.JButton botao_locacao_editar;
    private javax.swing.JButton botao_locacao_excluir;
    private javax.swing.JButton botao_locacao_novo;
    private javax.swing.JButton botao_locacao_salvar;
    private javax.swing.JButton botao_veiculo_cancelar;
    private javax.swing.JButton botao_veiculo_editar;
    private javax.swing.JButton botao_veiculo_excluir;
    private javax.swing.JButton botao_veiculo_novo;
    private javax.swing.JButton botao_veiculo_salvar;
    private javax.swing.JTextField campo_cliente_cpf;
    private javax.swing.JTextField campo_cliente_nacionalidade;
    private javax.swing.JTextField campo_cliente_nascimento;
    private javax.swing.JTextField campo_cliente_nome;
    private javax.swing.JTextField campo_cliente_rg;
    private javax.swing.JTextField campo_cliente_sexo;
    private javax.swing.JTextField campo_cliente_telefone;
    private javax.swing.JTextField campo_locacao_cpf;
    private javax.swing.JTextField campo_locacao_datadev;
    private javax.swing.JTextField campo_locacao_dataloc;
    private javax.swing.JTextField campo_locacao_horadev;
    private javax.swing.JTextField campo_locacao_horaloc;
    private javax.swing.JTextField campo_locacao_numloc;
    private javax.swing.JTextField campo_locacao_placa;
    private javax.swing.JTextField campo_veiculo_ano;
    private javax.swing.JTextField campo_veiculo_cor;
    private javax.swing.JTextField campo_veiculo_marca;
    private javax.swing.JTextField campo_veiculo_modelo;
    private javax.swing.JTextField campo_veiculo_placa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable table_cliente_clientes;
    private javax.swing.JTable table_locacao_locacoes;
    private javax.swing.JTable table_veiculo_veiculos;
    // End of variables declaration//GEN-END:variables
}
