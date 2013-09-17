package br.com.furb.copilador.tela;

import br.com.furb.copilador.actionCopilador.Action;
import br.com.furb.copilador.actionCopilador.LocateFile;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.KeyEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TelaPrincipal extends javax.swing.JFrame {

    private Action acoes;
    private File arquivoAtual;
    private LocateFile retornoAbrir = new LocateFile(this, "Abrir arquivo");
    private LocateFile retornoSalvar = new LocateFile(this, "Salvar arquivo");

    public TelaPrincipal() {
        initComponents();

        jSplitPane1.setEnabled(false);
        jSplitPane2.setEnabled(false);

        areaMensagemJTA.getDocument().addDocumentListener(new LimitLinesDocumentListener(5));
        editorJTP.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                acoes.atualizarBarraStatus(Action.getDsModification());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                acoes.atualizarBarraStatus(Action.getDsModification());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                acoes.atualizarBarraStatus(Action.getDsModification());
            }
        });

        TextLineNumber tln = new TextLineNumber(editorJTP);
        jScrollPane3.setRowHeaderView(tln);

        acoes = new Action(this);
        acoes.inicializaPrograma();

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent evt) {

                if ((evt.getKeyCode() == KeyEvent.VK_N) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    acoes.doClickNovo();
                } else if ((evt.getKeyCode() == KeyEvent.VK_A) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    File retorno = retornoAbrir.showDialog(0);
                    if (retorno != null) {
                        setArquivoAtual(retorno);
                        acoes.doClickAbrir();
                    }
                    acoes.doClickAbrir();
                } else if ((evt.getKeyCode() == KeyEvent.VK_S) && ((evt.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if (getArquivoAtual() == null) {
                        File retorno = retornoSalvar.showDialog(1);
                        setArquivoAtual(retorno);
                    }
                    acoes.doClickSalvar();
                } else if (evt.getKeyCode() == KeyEvent.VK_F8) {
                    acoes.doClickCompilar();
                } else if (evt.getKeyCode() == KeyEvent.VK_F9) {
                    acoes.doClickGerarCodigo();
                } else if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    acoes.doClickEquipe();
                }
                return false;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        barraFerramenteJP = new javax.swing.JPanel();
        novoJB = new javax.swing.JButton();
        abrirJB = new javax.swing.JButton();
        salvarJB = new javax.swing.JButton();
        copiarJB = new javax.swing.JButton();
        colarJB = new javax.swing.JButton();
        recortarJB = new javax.swing.JButton();
        compilarJB = new javax.swing.JButton();
        gerarCodigoJB = new javax.swing.JButton();
        equipeJB = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        editorJTP = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMensagemJTA = new javax.swing.JTextArea();
        barraStatusJP = new javax.swing.JPanel();
        dsModificacaoJL = new javax.swing.JLabel();
        dsDiretorioJL = new javax.swing.JLabel();
        dsNomeArquivoJL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Copilador");
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(50);
        jSplitPane1.setDividerSize(1);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        barraFerramenteJP.setName(""); // NOI18N
        barraFerramenteJP.setLayout(new java.awt.GridLayout(1, 9));

        novoJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        novoJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/novo.png"))); // NOI18N
        novoJB.setText("Novo [ctrl+n]");
        novoJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        novoJB.setIconTextGap(0);
        novoJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        novoJB.setVerifyInputWhenFocusTarget(false);
        novoJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        novoJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                novoJBMouseClicked(evt);
            }
        });
        novoJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(novoJB);

        abrirJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        abrirJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/abrir.png"))); // NOI18N
        abrirJB.setText("Abrir [ctrl+a]");
        abrirJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abrirJB.setIconTextGap(0);
        abrirJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        abrirJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        abrirJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirJBMouseClicked(evt);
            }
        });
        abrirJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(abrirJB);

        salvarJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        salvarJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/salvar.png"))); // NOI18N
        salvarJB.setText("Salvar [ctrl+s]");
        salvarJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salvarJB.setIconTextGap(0);
        salvarJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        salvarJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salvarJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salvarJBMouseClicked(evt);
            }
        });
        salvarJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(salvarJB);

        copiarJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        copiarJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/copiar.png"))); // NOI18N
        copiarJB.setText("Copiar [ctrl+c]");
        copiarJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copiarJB.setIconTextGap(0);
        copiarJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        copiarJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copiarJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(copiarJB);

        colarJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        colarJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/colar.png"))); // NOI18N
        colarJB.setText("Colar [ctrl+v]");
        colarJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        colarJB.setIconTextGap(0);
        colarJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        colarJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        colarJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colarJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(colarJB);

        recortarJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        recortarJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/recortar.png"))); // NOI18N
        recortarJB.setText("Recortar [ctrl+x]");
        recortarJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        recortarJB.setIconTextGap(1);
        recortarJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        recortarJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        recortarJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recortarJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(recortarJB);

        compilarJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        compilarJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/compilar.png"))); // NOI18N
        compilarJB.setText("Compilar [F8]");
        compilarJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        compilarJB.setIconTextGap(0);
        compilarJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        compilarJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        compilarJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                compilarJBMouseClicked(evt);
            }
        });
        compilarJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(compilarJB);

        gerarCodigoJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gerarCodigoJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/gerarcodigo.png"))); // NOI18N
        gerarCodigoJB.setText("Gerar código [F9]");
        gerarCodigoJB.setAlignmentY(0.0F);
        gerarCodigoJB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gerarCodigoJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gerarCodigoJB.setIconTextGap(0);
        gerarCodigoJB.setMargin(new java.awt.Insets(0, 0, 2, 0));
        gerarCodigoJB.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gerarCodigoJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gerarCodigoJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gerarCodigoJBMouseClicked(evt);
            }
        });
        gerarCodigoJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarCodigoJBActionPerformed(evt);
            }
        });
        barraFerramenteJP.add(gerarCodigoJB);

        equipeJB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        equipeJB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/equipe.png"))); // NOI18N
        equipeJB.setText("Equipe [F1]");
        equipeJB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        equipeJB.setIconTextGap(0);
        equipeJB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        equipeJB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        equipeJB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipeJBMouseClicked(evt);
            }
        });
        barraFerramenteJP.add(equipeJB);

        jSplitPane1.setTopComponent(barraFerramenteJP);

        jSplitPane2.setDividerLocation(375);
        jSplitPane2.setDividerSize(1);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setResizeWeight(1.0);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        editorJTP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editorJTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editorJTPKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(editorJTP);

        jSplitPane2.setLeftComponent(jScrollPane3);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        areaMensagemJTA.setEditable(false);
        areaMensagemJTA.setColumns(20);
        areaMensagemJTA.setRows(5);
        areaMensagemJTA.setTabSize(5);
        jScrollPane2.setViewportView(areaMensagemJTA);

        jSplitPane2.setRightComponent(jScrollPane2);

        jSplitPane1.setRightComponent(jSplitPane2);

        jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        barraStatusJP.setPreferredSize(new java.awt.Dimension(100, 30));
        barraStatusJP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        barraStatusJP.add(dsModificacaoJL);
        barraStatusJP.add(dsDiretorioJL);
        barraStatusJP.add(dsNomeArquivoJL);

        getContentPane().add(barraStatusJP, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compilarJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_compilarJBMouseClicked
        acoes.doClickCompilar();
    }//GEN-LAST:event_compilarJBMouseClicked

    private void gerarCodigoJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gerarCodigoJBMouseClicked
        acoes.doClickGerarCodigo();
    }//GEN-LAST:event_gerarCodigoJBMouseClicked

    private void equipeJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipeJBMouseClicked
        acoes.doClickEquipe();
    }//GEN-LAST:event_equipeJBMouseClicked

    private void novoJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_novoJBMouseClicked
        acoes.doClickNovo();
    }//GEN-LAST:event_novoJBMouseClicked

    private void abrirJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirJBMouseClicked
        File retorno = retornoAbrir.showDialog(0);
        if (retorno != null) {
            setArquivoAtual(retorno);
            acoes.doClickAbrir();
        }
    }//GEN-LAST:event_abrirJBMouseClicked

    private void salvarJBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvarJBMouseClicked
        if (getArquivoAtual() == null) {
            File retorno = retornoSalvar.showDialog(1);
            if (retorno == null) {
                return;
            }
            setArquivoAtual(retorno);
        }
        acoes.doClickSalvar();
    }//GEN-LAST:event_salvarJBMouseClicked

    private void editorJTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editorJTPKeyPressed
    }//GEN-LAST:event_editorJTPKeyPressed

    private void copiarJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarJBActionPerformed
        acoes.doClickCopiar();
    }//GEN-LAST:event_copiarJBActionPerformed

    private void colarJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colarJBActionPerformed
        acoes.doClickColar();
    }//GEN-LAST:event_colarJBActionPerformed

    private void recortarJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recortarJBActionPerformed
        acoes.doClickRecortar();
    }//GEN-LAST:event_recortarJBActionPerformed

    private void gerarCodigoJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarCodigoJBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gerarCodigoJBActionPerformed

    private void compilarJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compilarJBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compilarJBActionPerformed

    private void salvarJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarJBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salvarJBActionPerformed

    private void abrirJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirJBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abrirJBActionPerformed

    private void novoJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoJBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_novoJBActionPerformed

    public JLabel getDsDiretorioJL() {
        return dsDiretorioJL;
    }

    public JLabel getDsModificacaoJL() {
        return dsModificacaoJL;
    }

    public JLabel getDsNomeArquivoJL() {
        return dsNomeArquivoJL;
    }

    public JEditorPane getEditorJTP() {
        return editorJTP;
    }

    public JTextArea getAreaMensagemJTA() {
        return areaMensagemJTA;
    }

    public File getArquivoAtual() {
        return arquivoAtual;
    }

    public void setArquivoAtual(File arquivoAtual) {
        this.arquivoAtual = arquivoAtual;
    }

    public String getEditorSelectedText() {
        return editorJTP.getSelectedText();
    }

    public void eraseEditorSelectedText() {
        editorJTP.replaceSelection("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirJB;
    private javax.swing.JTextArea areaMensagemJTA;
    private javax.swing.JPanel barraFerramenteJP;
    private javax.swing.JPanel barraStatusJP;
    private javax.swing.JButton colarJB;
    private javax.swing.JButton compilarJB;
    private javax.swing.JButton copiarJB;
    private javax.swing.JLabel dsDiretorioJL;
    private javax.swing.JLabel dsModificacaoJL;
    private javax.swing.JLabel dsNomeArquivoJL;
    private javax.swing.JEditorPane editorJTP;
    private javax.swing.JButton equipeJB;
    private javax.swing.JButton gerarCodigoJB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton novoJB;
    private javax.swing.JButton recortarJB;
    private javax.swing.JButton salvarJB;
    // End of variables declaration//GEN-END:variables
}
