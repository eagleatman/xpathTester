import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class XpathTester {
    private JPanel panel1;
    private JTextArea xmlTextArea;
    private JTextArea resultTextArea;
    private JTextField xpathTextField;
    private JButton xpathTestButton;
    private JScrollPane scrollPane1;

    public XpathTester() {
        xpathTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String xmlAreaText = xmlTextArea.getText();
                String message = "";
                String xpath = xpathTextField.getText();
                if (!xmlAreaText.equals("") && !xpath.equals("")) {
                    resultTextArea.setText("");
                    try {
                        Document doc = DocumentHelper.parseText(xmlAreaText);
                        //Document doc1 = new SAXReader().read("src/main/resources/blog.xml");
                        Element ele = doc.getRootElement();
                        List<Node> nodes = ele.selectNodes(xpath);
                        for (Node node : nodes) {
                            // 全部xml内容
                            if (node.asXML().equals(ele.asXML())) {
                                resultTextArea.setText(node.asXML().replace("\t", "    ")+"\n");
                                break;
                            } else {
                                // 美化结果 将制表符换成空格符，同时，最后去除最后一个标签的前面空格
                                resultTextArea.append(node.asXML().replace("\t", "    ").replace("    </","  </")+"\n");
                            }
                        }
                    } catch (DocumentException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    message = "请输入xml源数据及xpath语法！！！";
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("xpath语法测试@e0g18");
        frame.setContentPane(new XpathTester().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
