/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import json.JSONArray;
import json.JSONObject;
import model.bean.Leasing;
import model.dao.LeasingDAO;

/**
 *
 * @author Murilo
 */
public class ViewLeasing extends javax.swing.JFrame {

    String mode;
    String option = "";
    private String selected = "Portuguese";

    /**
     * Creates new form viewLeasing
     */
    public ViewLeasing(String language) {
        initComponents();
        setLocationRelativeTo(null);
        this.selected = language;
        changeLanguage(selected);
        startWindowLeasing();
        readJTable();
        mode = "Free";
        manipulateInterfaceLeasing();
        DefaultTableModel model = (DefaultTableModel) table_leasing_leasings.getModel();
        table_leasing_leasings.setRowSorter(new TableRowSorter(model));
    }

    public void clearFieldsLeasing() {
        field_leasing_date_leasing.setText("");
        field_leasing_time_leasing.setText("");
        field_leasing_date_devolution.setText("");
        field_leasing_time_devolution.setText("");
        field_leasing_number_leasing.setText("");
        field_leasing_cpf_leasing.setText("");
        field_leasing_plate_leasing.setText("");
    }

    private void changeLanguage(String language) {
        switch (language) {
            case "Portuguese":
                button_leasing_cancel.setText("Cancelar");
                button_leasing_delete.setText("Excluir");
                button_leasing_edit.setText("Editar");
                button_leasing_export.setText("Exportar");
                button_leasing_import.setText("Importar");
                button_leasing_menu.setText("Menu principal");
                button_leasing_new.setText("Novo");
                button_leasing_save.setText("Salvar");
                button_leasing_search.setText("Buscar por CPF");
                panel_leasing.setName("Locação");
                label_cpf_leasing.setText("CPF Locação:");
                label_date_devolution.setText("Data Devolução:");
                label_date_leasing.setText("Data Locação:");
                label_number_leasing.setText("Número Locação:");
                label_plate_leasing.setText("Placa Locação:");
                label_time_devolution.setText("Hora Devolução:");
                label_time_leasing.setText("Hora Locação:");
                break;
            case "English":
                button_leasing_cancel.setText("Cancel");
                button_leasing_delete.setText("Delete");
                button_leasing_edit.setText("Edit");
                button_leasing_export.setText("Export");
                button_leasing_import.setText("Import");
                button_leasing_menu.setText("Main menu");
                button_leasing_new.setText("New");
                button_leasing_save.setText("Save");
                button_leasing_search.setText("Search by CPF");
                panel_leasing.setName("Leasing");
                label_cpf_leasing.setText("CPF Leasing:");
                label_date_devolution.setText("Date Devolution:");
                label_date_leasing.setText("Date Leasing:");
                label_number_leasing.setText("Number Leasing:");
                label_plate_leasing.setText("Plate Leasing:");
                label_time_devolution.setText("Time Devolution:");
                label_time_leasing.setText("Time Leasing:");
                break;
            case "Spanish":
                button_leasing_cancel.setText("Cancelar");
                button_leasing_delete.setText("Borrar");
                button_leasing_edit.setText("Editar");
                button_leasing_export.setText("Exportación");
                button_leasing_import.setText("Importación");
                button_leasing_menu.setText("Menú principal");
                button_leasing_new.setText("Nuevo");
                button_leasing_save.setText("Guardar");
                button_leasing_search.setText("Buscar por CPF");
                panel_leasing.setName("Arrendamiento");
                label_cpf_leasing.setText("CPF Alquiler:");
                label_date_devolution.setText("Fecha Devolución:");
                label_date_leasing.setText("Fecha de Alquiler:");
                label_number_leasing.setText("Número Alquiler:");
                label_plate_leasing.setText("Placa Alquiler:");
                label_time_devolution.setText("Hora Devolución:");
                label_time_leasing.setText("Hora Alquiler:");
                break;
            default:

        }
    }

    public void readJTable() {

        DefaultTableModel model = (DefaultTableModel) table_leasing_leasings.getModel();
        model.setNumRows(0);
        LeasingDAO pdao = new LeasingDAO();

        for (Leasing p : pdao.read()) {

            model.addRow(new Object[]{
                p.getNumberLeasing(),
                p.getCpfLeasing(),
                p.getPlateLeasing(),
                p.getDateLeasing(),
                p.getTimeLeasing(),
                p.getDateDevolution(),
                p.getTimeDevolution()
            });

        }

    }

    public void manipulateInterfaceLeasing() {
        switch (mode) {
            case "Free":
                button_leasing_save.setEnabled(false);
                button_leasing_cancel.setEnabled(false);
                button_leasing_new.setEnabled(true);
                button_leasing_edit.setEnabled(false);
                button_leasing_delete.setEnabled(false);
                field_leasing_date_leasing.setEnabled(false);
                field_leasing_time_leasing.setEnabled(false);
                field_leasing_date_devolution.setEnabled(false);
                field_leasing_time_devolution.setEnabled(false);
                field_leasing_number_leasing.setEnabled(false);
                field_leasing_cpf_leasing.setEnabled(false);
                field_leasing_plate_leasing.setEnabled(false);
                break;
            case "New":
                button_leasing_save.setEnabled(true);
                button_leasing_cancel.setEnabled(true);
                button_leasing_new.setEnabled(false);
                button_leasing_edit.setEnabled(false);
                button_leasing_delete.setEnabled(false);
                field_leasing_date_leasing.setEnabled(true);
                field_leasing_time_leasing.setEnabled(true);
                field_leasing_date_devolution.setEnabled(true);
                field_leasing_time_devolution.setEnabled(true);
                field_leasing_number_leasing.setEnabled(false);
                field_leasing_cpf_leasing.setEnabled(true);
                field_leasing_plate_leasing.setEnabled(true);
                break;
            case "Edit":
                button_leasing_save.setEnabled(true);
                button_leasing_cancel.setEnabled(true);
                button_leasing_new.setEnabled(false);
                button_leasing_edit.setEnabled(false);
                button_leasing_delete.setEnabled(false);
                field_leasing_date_leasing.setEnabled(true);
                field_leasing_time_leasing.setEnabled(true);
                field_leasing_date_devolution.setEnabled(true);
                field_leasing_time_devolution.setEnabled(true);
                field_leasing_number_leasing.setEnabled(false);
                field_leasing_cpf_leasing.setEnabled(true);
                field_leasing_plate_leasing.setEnabled(true);
                break;
            case "Delete":
                button_leasing_save.setEnabled(false);
                button_leasing_cancel.setEnabled(false);
                button_leasing_new.setEnabled(true);
                button_leasing_edit.setEnabled(false);
                button_leasing_delete.setEnabled(false);
                field_leasing_date_leasing.setEnabled(false);
                field_leasing_time_leasing.setEnabled(false);
                field_leasing_date_devolution.setEnabled(false);
                field_leasing_time_devolution.setEnabled(false);
                field_leasing_number_leasing.setEnabled(false);
                field_leasing_cpf_leasing.setEnabled(false);
                field_leasing_plate_leasing.setEnabled(false);
                break;
            case "Selection":
                button_leasing_save.setEnabled(false);
                button_leasing_cancel.setEnabled(false);
                button_leasing_new.setEnabled(true);
                button_leasing_edit.setEnabled(true);
                button_leasing_delete.setEnabled(true);
                field_leasing_date_leasing.setEnabled(false);
                field_leasing_time_leasing.setEnabled(false);
                field_leasing_date_devolution.setEnabled(false);
                field_leasing_time_devolution.setEnabled(false);
                field_leasing_number_leasing.setEnabled(false);
                field_leasing_cpf_leasing.setEnabled(false);
                field_leasing_plate_leasing.setEnabled(false);
                break;
            default:
                System.out.println("Mode invalid");
        }
    }

    public void startWindowLeasing() {
        field_leasing_date_leasing.setText("");
        field_leasing_time_leasing.setText("");
        field_leasing_date_devolution.setText("");
        field_leasing_time_devolution.setText("");
        field_leasing_number_leasing.setText("");
        field_leasing_cpf_leasing.setText("");
        field_leasing_plate_leasing.setText("");
        button_leasing_menu.setEnabled(true);
        button_leasing_import.setEnabled(true);
        button_leasing_export.setEnabled(true);
    }

    public void recordInformationLeasing() {
        File file = new File("Leasing.json");
        String content;
        try {

            LeasingDAO pdao = new LeasingDAO();
            FileWriter f = new FileWriter(file, true);
            for (Leasing p : pdao.read()) {
                JSONObject json = new JSONObject();
                content = p.toJson().toString();
                content += "\r\n";
                f.write(content);
            }
            f.close();
            JOptionPane.showMessageDialog(null, "Data Exported Successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not perform this action");
        }
    }

    public void loadInformationLeasing() {
        try {
            LeasingDAO pdao = new LeasingDAO();
            FileReader fr = new FileReader("Leasing.json");
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                Leasing p = new Leasing();
                JSONArray json = new JSONArray(str.toString());
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    p.setNumberLeasing(obj.getInt("number_leasing"));
                    p.setCpfLeasing(obj.getString("cpf_leasing"));
                    p.setPlateLeasing(obj.getString("plate_leasing"));
                    p.setDateLeasing((Date) obj.get("date_leasing"));
                    p.setDateDevolution((Date) obj.get("date_devolution"));
                    p.setTimeLeasing((Time) obj.get("time_leasing"));
                    p.setTimeDevolution((Time) obj.get("time_devolution"));

                    if (i + 1 == json.length()) {
                        pdao.create(p);
                    }
                }
            }
            br.close();
            JOptionPane.showMessageDialog(null, "Data Imported Successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not perform this action");
        }
    }

    public void readTableByCpf(String cpf) {
        DefaultTableModel model = (DefaultTableModel) table_leasing_leasings.getModel();
        model.setNumRows(0);
        LeasingDAO pdao = new LeasingDAO();

        for (Leasing p : pdao.readByCpf(cpf)) {

            model.addRow(new Object[]{
                p.getNumberLeasing(),
                p.getCpfLeasing(),
                p.getPlateLeasing(),
                p.getDateLeasing(),
                p.getTimeLeasing(),
                p.getDateDevolution(),
                p.getTimeDevolution()
            });

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_leasing = new javax.swing.JPanel();
        label_date_leasing = new javax.swing.JLabel();
        field_leasing_date_leasing = new javax.swing.JTextField();
        label_time_leasing = new javax.swing.JLabel();
        field_leasing_time_leasing = new javax.swing.JTextField();
        label_date_devolution = new javax.swing.JLabel();
        label_time_devolution = new javax.swing.JLabel();
        label_number_leasing = new javax.swing.JLabel();
        field_leasing_date_devolution = new javax.swing.JTextField();
        field_leasing_time_devolution = new javax.swing.JTextField();
        field_leasing_number_leasing = new javax.swing.JTextField();
        button_leasing_save = new javax.swing.JButton();
        button_leasing_cancel = new javax.swing.JButton();
        label_cpf_leasing = new javax.swing.JLabel();
        field_leasing_cpf_leasing = new javax.swing.JTextField();
        label_plate_leasing = new javax.swing.JLabel();
        field_leasing_plate_leasing = new javax.swing.JTextField();
        button_leasing_menu = new javax.swing.JButton();
        button_leasing_export = new javax.swing.JButton();
        button_leasing_import = new javax.swing.JButton();
        button_leasing_delete = new javax.swing.JButton();
        button_leasing_edit = new javax.swing.JButton();
        button_leasing_new = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_leasing_leasings = new javax.swing.JTable();
        button_leasing_search = new javax.swing.JButton();
        field_leasing_search = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_leasing.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Locação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        panel_leasing.setPreferredSize(new java.awt.Dimension(750, 260));

        label_date_leasing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_date_leasing.setText("Data Locação:");

        field_leasing_date_leasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_leasing_date_leasingActionPerformed(evt);
            }
        });

        label_time_leasing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_time_leasing.setText("Hora Locação:");

        label_date_devolution.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_date_devolution.setText("Data Devolução:");

        label_time_devolution.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_time_devolution.setText("Hora Devolução:");

        label_number_leasing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_number_leasing.setText("Numero Locação:");

        field_leasing_date_devolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_leasing_date_devolutionActionPerformed(evt);
            }
        });

        field_leasing_time_devolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_leasing_time_devolutionActionPerformed(evt);
            }
        });

        button_leasing_save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_save.setText("Salvar");
        button_leasing_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_saveActionPerformed(evt);
            }
        });

        button_leasing_cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_cancel.setText("Cancelar");
        button_leasing_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_cancelActionPerformed(evt);
            }
        });

        label_cpf_leasing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_cpf_leasing.setText("CPF Locação:");

        label_plate_leasing.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_plate_leasing.setText("Placa Locação:");

        button_leasing_menu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_menu.setText("Menu principal");
        button_leasing_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_menuActionPerformed(evt);
            }
        });

        button_leasing_export.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_export.setText("Exportar Dados");
        button_leasing_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_exportActionPerformed(evt);
            }
        });

        button_leasing_import.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_import.setText("Importar Dados");
        button_leasing_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_importActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_leasingLayout = new javax.swing.GroupLayout(panel_leasing);
        panel_leasing.setLayout(panel_leasingLayout);
        panel_leasingLayout.setHorizontalGroup(
            panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leasingLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_time_leasing)
                    .addComponent(label_date_devolution)
                    .addComponent(label_date_leasing)
                    .addComponent(label_time_devolution)
                    .addComponent(label_number_leasing)
                    .addComponent(label_cpf_leasing)
                    .addComponent(label_plate_leasing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_leasing_date_devolution)
                    .addComponent(field_leasing_time_devolution, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(field_leasing_number_leasing, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field_leasing_time_leasing)
                    .addComponent(field_leasing_date_leasing)
                    .addComponent(field_leasing_cpf_leasing, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(field_leasing_plate_leasing, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(2, 2, 2)
                .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_leasingLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(button_leasing_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_leasingLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button_leasing_export, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_leasing_save, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button_leasing_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(button_leasing_import, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(82, 82, 82))
        );
        panel_leasingLayout.setVerticalGroup(
            panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_leasingLayout.createSequentialGroup()
                .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_leasingLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_leasing_export)
                            .addComponent(button_leasing_import))
                        .addGap(18, 18, 18)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_leasing_cancel)
                            .addComponent(button_leasing_save))
                        .addGap(54, 54, 54)
                        .addComponent(button_leasing_menu))
                    .addGroup(panel_leasingLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_date_leasing)
                            .addComponent(field_leasing_date_leasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_leasing_time_leasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_time_leasing))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_leasing_date_devolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_date_devolution))
                        .addGap(11, 11, 11)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_time_devolution)
                            .addComponent(field_leasing_time_devolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_number_leasing)
                            .addComponent(field_leasing_number_leasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_cpf_leasing)
                            .addComponent(field_leasing_cpf_leasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_leasingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_plate_leasing)
                            .addComponent(field_leasing_plate_leasing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        button_leasing_delete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_delete.setText("Excluir");
        button_leasing_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_deleteActionPerformed(evt);
            }
        });

        button_leasing_edit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_edit.setText("Editar");
        button_leasing_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_editActionPerformed(evt);
            }
        });

        button_leasing_new.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_new.setText("Novo");
        button_leasing_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_newActionPerformed(evt);
            }
        });

        table_leasing_leasings.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table_leasing_leasings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número Locação", "Cpf Cliente", "Placa Veículo", "Data Locação", "Hora Locação", "Data Devolução", "Hora Devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_leasing_leasings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_leasing_leasingsMouseClicked(evt);
            }
        });
        table_leasing_leasings.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_leasing_leasingsKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(table_leasing_leasings);
        if (table_leasing_leasings.getColumnModel().getColumnCount() > 0) {
            table_leasing_leasings.getColumnModel().getColumn(0).setHeaderValue("Número Locação");
            table_leasing_leasings.getColumnModel().getColumn(1).setHeaderValue("Cpf Cliente");
            table_leasing_leasings.getColumnModel().getColumn(2).setHeaderValue("Placa Veículo");
            table_leasing_leasings.getColumnModel().getColumn(3).setHeaderValue("Data Locação");
            table_leasing_leasings.getColumnModel().getColumn(4).setHeaderValue("Hora Locação");
            table_leasing_leasings.getColumnModel().getColumn(5).setHeaderValue("Data Devolução");
            table_leasing_leasings.getColumnModel().getColumn(6).setResizable(false);
            table_leasing_leasings.getColumnModel().getColumn(6).setHeaderValue("Hora Devolução");
        }

        button_leasing_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        button_leasing_search.setText("Buscar por CPF");
        button_leasing_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_leasing_searchActionPerformed(evt);
            }
        });

        field_leasing_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_leasing_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button_leasing_new, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_leasing_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_leasing_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(field_leasing_search, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(button_leasing_search)
                .addContainerGap())
            .addComponent(jScrollPane3)
            .addComponent(panel_leasing, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_leasing_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_leasing_search)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(button_leasing_new)
                        .addComponent(button_leasing_edit)
                        .addComponent(button_leasing_delete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_leasing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_leasing_date_leasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_leasing_date_leasingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_leasing_date_leasingActionPerformed

    private void field_leasing_date_devolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_leasing_date_devolutionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_leasing_date_devolutionActionPerformed

    private void field_leasing_time_devolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_leasing_time_devolutionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_leasing_time_devolutionActionPerformed

    private void button_leasing_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_saveActionPerformed
        // Formatar times
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        java.util.Date hLoc = null;
        java.util.Date hDev = null;
        try {
            hLoc = formatter.parse(field_leasing_time_leasing.getText());
            hDev = formatter.parse(field_leasing_time_devolution.getText());
        } catch (ParseException ex) {
            Logger.getLogger(ViewLeasing.class.getName()).log(Level.SEVERE, null, ex);
        }

        Time time1 = new Time(hLoc.getTime());
        Time time2 = new Time(hDev.getTime());
        // Formatar dates
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateloc = null;
        java.sql.Date datedev = null;
        try {
            dateloc = new java.sql.Date(fmt.parse(field_leasing_date_leasing.getText()).getTime());
            datedev = new java.sql.Date(fmt.parse(field_leasing_date_devolution.getText()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ViewLeasing.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (option == "Edit") {
            Leasing p = new Leasing();
            LeasingDAO dao = new LeasingDAO();
            p.setCpfLeasing(field_leasing_cpf_leasing.getText());
            p.setPlateLeasing(field_leasing_plate_leasing.getText());
            p.setDateLeasing(dateloc);
            p.setDateDevolution(datedev);
            p.setTimeLeasing(time1);
            p.setTimeDevolution(time2);
            dao.update(p);
            readJTable();
            mode = "Free";
            option = "";
            manipulateInterfaceLeasing();
            clearFieldsLeasing();
        }

        if (option == "Save") {
            Leasing p = new Leasing();
            LeasingDAO dao = new LeasingDAO();
            p.setCpfLeasing(field_leasing_cpf_leasing.getText());
            p.setPlateLeasing(field_leasing_plate_leasing.getText());
            p.setDateLeasing(dateloc);
            p.setDateDevolution(datedev);
            p.setTimeLeasing(time1);
            p.setTimeDevolution(time2);
            dao.create(p);
            clearFieldsLeasing();
            readJTable();
            mode = "Free";
            manipulateInterfaceLeasing();
            option = "";
        }

    }//GEN-LAST:event_button_leasing_saveActionPerformed

    private void button_leasing_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_cancelActionPerformed
        clearFieldsLeasing();
        mode = "Free";
        manipulateInterfaceLeasing();
    }//GEN-LAST:event_button_leasing_cancelActionPerformed

    private void button_leasing_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_deleteActionPerformed
        if (table_leasing_leasings.getSelectedRow() != -1) {
            Leasing p = new Leasing();
            LeasingDAO dao = new LeasingDAO();
            p.setNumberLeasing((int) table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 0));
            dao.delete(p);
            clearFieldsLeasing();
            readJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Select a product to exclude.");
        }
        mode = "Free";
    }//GEN-LAST:event_button_leasing_deleteActionPerformed

    private void button_leasing_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_editActionPerformed
        mode = "Edit";
        option = "Edit";
        manipulateInterfaceLeasing();

    }//GEN-LAST:event_button_leasing_editActionPerformed

    private void button_leasing_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_newActionPerformed
        clearFieldsLeasing();
        mode = "New";
        option = "Save";
        manipulateInterfaceLeasing();
    }//GEN-LAST:event_button_leasing_newActionPerformed

    private void table_leasing_leasingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_leasing_leasingsMouseClicked
        mode = "Selection";
        manipulateInterfaceLeasing();
        if (table_leasing_leasings.getSelectedRow() != -1) {

            field_leasing_number_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 0).toString());
            field_leasing_cpf_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 1).toString());
            field_leasing_plate_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 2).toString());
            field_leasing_date_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 3).toString());
            field_leasing_time_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 4).toString());
            field_leasing_date_devolution.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 5).toString());
            field_leasing_time_devolution.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 6).toString());
        }
    }//GEN-LAST:event_table_leasing_leasingsMouseClicked

    private void button_leasing_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_searchActionPerformed
        readTableByCpf(field_leasing_search.getText());
    }//GEN-LAST:event_button_leasing_searchActionPerformed

    private void field_leasing_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_leasing_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_leasing_searchActionPerformed

    private void table_leasing_leasingsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_leasing_leasingsKeyReleased
        if (table_leasing_leasings.getSelectedRow() != -1) {

            field_leasing_number_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 0).toString());
            field_leasing_cpf_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 1).toString());
            field_leasing_plate_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 2).toString());
            field_leasing_date_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 3).toString());
            field_leasing_time_leasing.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 4).toString());
            field_leasing_date_devolution.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 5).toString());
            field_leasing_time_devolution.setText(table_leasing_leasings.getValueAt(table_leasing_leasings.getSelectedRow(), 6).toString());
        }
    }//GEN-LAST:event_table_leasing_leasingsKeyReleased

    private void button_leasing_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_menuActionPerformed
        new ViewHome(selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_leasing_menuActionPerformed

    private void button_leasing_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_exportActionPerformed
        //recordInformationLeasing();
        String window = "Leasing";
        new ViewExport(window, selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_leasing_exportActionPerformed

    private void button_leasing_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_leasing_importActionPerformed
        //loadInformationLeasing();
        String window = "Leasing";
        new ViewImport(window, selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_leasing_importActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLeasing("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_leasing_cancel;
    private javax.swing.JButton button_leasing_delete;
    private javax.swing.JButton button_leasing_edit;
    private javax.swing.JButton button_leasing_export;
    private javax.swing.JButton button_leasing_import;
    private javax.swing.JButton button_leasing_menu;
    private javax.swing.JButton button_leasing_new;
    private javax.swing.JButton button_leasing_save;
    private javax.swing.JButton button_leasing_search;
    private javax.swing.JTextField field_leasing_cpf_leasing;
    private javax.swing.JTextField field_leasing_date_devolution;
    private javax.swing.JTextField field_leasing_date_leasing;
    private javax.swing.JTextField field_leasing_number_leasing;
    private javax.swing.JTextField field_leasing_plate_leasing;
    private javax.swing.JTextField field_leasing_search;
    private javax.swing.JTextField field_leasing_time_devolution;
    private javax.swing.JTextField field_leasing_time_leasing;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_cpf_leasing;
    private javax.swing.JLabel label_date_devolution;
    private javax.swing.JLabel label_date_leasing;
    private javax.swing.JLabel label_number_leasing;
    private javax.swing.JLabel label_plate_leasing;
    private javax.swing.JLabel label_time_devolution;
    private javax.swing.JLabel label_time_leasing;
    private javax.swing.JPanel panel_leasing;
    private javax.swing.JTable table_leasing_leasings;
    // End of variables declaration//GEN-END:variables
}
