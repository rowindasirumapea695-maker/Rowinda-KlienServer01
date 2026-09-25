package clientserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientServer extends JFrame {

    private JTextField txtNama;
    private JTextField txtNoHP;
    private JTextField txtAlamat;
    private JTextField txtJumlah;
    private JTextField txtTotal;

    private JComboBox<String> comboLayanan;

    private JButton btnProses;
    private JButton btnSimpan;
    private JButton btnBersih;

    private JTable tabel;
    private DefaultTableModel model;

    public ClientServer() {

        setTitle("ClientServer - Sistem Jasa Kebersihan");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buatTampilan();
        buatTombol();
    }

    private void buatTampilan() {

        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BorderLayout(10, 10));

        JLabel judul = new JLabel(
                "CLIENT SERVER",
                SwingConstants.CENTER
        );

        judul.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        panelUtama.add(judul, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(
                new GridLayout(6, 2, 8, 8)
        );

        panelForm.setBorder(
                BorderFactory.createTitledBorder(
                        "Data Pelanggan"
                )
        );

        panelForm.add(new JLabel("Nama Pelanggan"));

        txtNama = new JTextField();
        panelForm.add(txtNama);


        panelForm.add(new JLabel("No. HP"));

        txtNoHP = new JTextField();
        panelForm.add(txtNoHP);


        panelForm.add(new JLabel("Alamat"));

        txtAlamat = new JTextField();
        panelForm.add(txtAlamat);


        panelForm.add(new JLabel("Layanan"));

        comboLayanan = new JComboBox<>();

        comboLayanan.addItem("Pilih Layanan");
        comboLayanan.addItem("Cuci Rumah");
        comboLayanan.addItem("Cuci Kantor");
        comboLayanan.addItem("Cuci Sofa");
        comboLayanan.addItem("Cuci Karpet");

        panelForm.add(comboLayanan);


        panelForm.add(new JLabel("Jumlah"));

        txtJumlah = new JTextField();
        panelForm.add(txtJumlah);


        panelForm.add(new JLabel("Total Harga"));

        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        panelForm.add(txtTotal);


        panelUtama.add(
                panelForm,
                BorderLayout.WEST
        );

        model = new DefaultTableModel();

        model.addColumn("Nama");
        model.addColumn("No HP");
        model.addColumn("Layanan");
        model.addColumn("Jumlah");
        model.addColumn("Total");


        tabel = new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Data Transaksi"
                )
        );


        panelUtama.add(
                scroll,
                BorderLayout.CENTER
        );

        JPanel panelTombol =
                new JPanel();

        btnProses =
                new JButton("HITUNG");

        btnSimpan =
                new JButton("SIMPAN");

        btnBersih =
                new JButton("BERSIHKAN");


        panelTombol.add(btnProses);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnBersih);


        panelUtama.add(
                panelTombol,
                BorderLayout.SOUTH
        );


        add(panelUtama);
    }
    
    private void buatTombol() {

        btnProses.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                hitungHarga();
            }
        });

        btnSimpan.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                simpanData();
            }
        });

        btnBersih.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(
                    ActionEvent e) {

                bersihkanForm();
            }
        });
    }

    private void hitungHarga() {

        try {

            int jumlah =
                    Integer.parseInt(
                            txtJumlah.getText()
                    );


            String layanan =
                    comboLayanan
                            .getSelectedItem()
                            .toString();


            int harga = 0;


            switch (layanan) {

                case "Cuci Rumah":
                    harga = 100000;
                    break;

                case "Cuci Kantor":
                    harga = 150000;
                    break;

                case "Cuci Sofa":
                    harga = 75000;
                    break;

                case "Cuci Karpet":
                    harga = 50000;
                    break;

                default:

                    JOptionPane.showMessageDialog(
                            this,
                            "Silakan pilih layanan!"
                    );

                    return;
            }


            int total =
                    harga * jumlah;


            txtTotal.setText(
                    String.valueOf(total)
            );


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Jumlah harus berupa angka!"
            );
        }
    }

    private void simpanData() {

        if (txtNama.getText().isEmpty()
                || txtNoHP.getText().isEmpty()
                || txtAlamat.getText().isEmpty()
                || txtJumlah.getText().isEmpty()
                || txtTotal.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data belum lengkap!"
            );

            return;
        }


        model.addRow(
                new Object[]{

                    txtNama.getText(),

                    txtNoHP.getText(),

                    comboLayanan
                            .getSelectedItem(),

                    txtJumlah.getText(),

                    txtTotal.getText()
                }
        );


        JOptionPane.showMessageDialog(
                this,
                "Data berhasil disimpan!"
        );
    }

    private void bersihkanForm() {

        txtNama.setText("");

        txtNoHP.setText("");

        txtAlamat.setText("");

        txtJumlah.setText("");

        txtTotal.setText("");

        comboLayanan.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override
            public void run() {

                new ClientServer()
                        .setVisible(true);
            }
        });
    }
}