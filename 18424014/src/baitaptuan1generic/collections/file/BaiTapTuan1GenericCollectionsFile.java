/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitaptuan1generic.collections.file;

import form.*;
import java.awt.EventQueue;

/**
 *
 * @author Ha Chi
 */
public class BaiTapTuan1GenericCollectionsFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginForm lg = new LoginForm();
                lg.setVisible(true);
            }
        });
    }

}
