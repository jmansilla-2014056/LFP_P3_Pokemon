/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 *
 * @author Mansilla
 */
public class ImageString {
    
   private String s;
   private Icon icon;
   
   public ImageString(String first, ImageIcon imageIcon) {
    }
  
   public ImageString(String s, Icon icon) {
      this.s = s;
      this.icon = icon;
   }
  
   public void setString(String s) {
      this.s = s;
   }
  
   public String getString() {
      return s;
   }
  
   public void setIcon(Icon icon) {
      this.icon = icon;
   }
  
   public Icon getIcon() {
      return icon;
   }
   
   public String toString() {
      return s;
   }
    
    
    
}
