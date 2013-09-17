package br.com.furb.copilador.actionCopilador;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author aaschlote
 */
public class LocateFile extends JFileChooser {

    private Component parent;
    public static final int OPEN_DIALOG = 0;
    public static final int SAVE_DIALOG = 1;

    public LocateFile(Component parent, String titulo) {
        this.parent = parent;
        this.setDialogTitle(titulo);
    }

    public File showDialog(int operation) {
        File arquivo = null;
        if (operation == OPEN_DIALOG) {
            this.setFileSelectionMode(FILES_AND_DIRECTORIES);
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            if (this.showOpenDialog(this.parent) == this.APPROVE_OPTION) {
                arquivo = this.getSelectedFile();
            }
        } else {
            this.setFileSelectionMode(FILES_ONLY);
            if (this.showSaveDialog(this.parent) == this.APPROVE_OPTION) {
                arquivo = this.getSelectedFile();
            }

        }

        return arquivo;
    }
    
}
