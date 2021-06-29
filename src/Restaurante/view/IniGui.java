package restaurante.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import restaurante.view.empleado.GuiEmplCtrl;


public class IniGui extends JDialog{
	private static final long serialVersionUID = 1L;
	int status = -1;
	private GuiEmplCtrl emp;
	public IniGui(Frame frame) {
		super(frame, true);
		emp = new GuiEmplCtrl(status);
		this.setResizable(false);
		initGUI();
	}
   private void initGUI() {
	   
       String[] labels = {"DNI: ", "Id"};
       int numPairs = labels.length;
       JPanel p = new JPanel(new SpringLayout());
       JLabel l = new JLabel(labels[0], JLabel.TRAILING);
       p.add(l);
       JTextField a = new JTextField(9);
       l.setLabelFor(a);
       p.add(a);
       JLabel l1 = new JLabel(labels[1], JLabel.TRAILING);
       p.add(l1);
       JPasswordField b = new JPasswordField();
       l1.setLabelFor(b);
       p.add(b);
       
       SpringUtilities.makeCompactGrid(p,numPairs, 2, 6, 6, 6, 6);   
       JPanel opt = new JPanel();
       JButton ok = new JButton("OK");
       ok.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(emp.buscEnc(a.getText(),Integer.parseInt(b.getText()))) {
						status = 0;
						setVisible(false);
					}
					else if(emp.buscEmp(a.getText(),Integer.parseInt(b.getText()))){
						status = 1;
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Dni o Id incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE, null);
						status = -1;
						setVisible(true);
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
       JButton cancel = new JButton("Cancel");
       cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {setVisible(false);}});
       opt.add(ok);
       opt.add(cancel);
       p.setOpaque(true);  
       this.add(p,BorderLayout.CENTER);
       this.add(opt,BorderLayout.PAGE_END);
       pack();
       setVisible(false);
   }
   public int open() {
	   pack();
       setVisible(true);
       return status;
   }

}


