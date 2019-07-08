package org.simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*data.Node = package buatan sendiri*/

class Petunjuk extends JFrame
{
    private JTextArea area;

    public Petunjuk()
    {
        super("Petunjuk penggunaan");
        setSize(300,294);
        setVisible(true);
        setLayout(new BorderLayout());
        area=new JTextArea();
        area.setRows(16);
        area.setEditable(false);
        
        String isi =""+
                    "1. Untuk melakukan simulasi, pertama tekan input\n"+ 
                    "   dan input datanya lalu masukkan keyword yang\n"+ 
                    "   dicari lalu search, setelah selesai tekan reset.\n"+
                    "\n"+
                    "2. Untuk input atau keyword, jika sudah menekan\n"+ 
                    "   tombol input atau keyword namum tidak jadi,\n"+ 
                    "   maka cukup dengan mengisi input atau keyword\n"+ 
                    "   dengan sembarang angka [1-99] setelah itu\n"+ 
                    "   tekan reset untuk menghapus angka tadi. Input\n"+ 
                    "   data dan keyword harus berupa angka [1-99] dan\n"+ 
                    "   tidak bisa kosong atau berupa huruf.\n"+
                    "\n"+
                    "3. Untuk search, jika tombol search sudah ditekan\n"+ 
                    "   maka simulasi akan berjalan dan tidak dapat\n"+ 
                    "   keluar sampai simulasi selesai. Search dapat\n"+ 
                    "   dilakukan jika sudah memasukkan input dan\n"+ 
                    "   keyword.\n"+
                    "\n"+
                    "4. Untuk reset, jika tombol reset ditekan maka\n"+ 
                    "   semua data yang sudah diinput dan keyword akan\n"+ 
                    "   terhapus.";
        area.setText(isi);
        add(new JScrollPane(area),BorderLayout.NORTH);
    }
}

class SequentialSearchPanel extends JPanel
{
    private Node node[] = new Node[6];
    private int n;
    private int pointer=0;
    private int key;
    private String kalimat="";
    private int index;
    private boolean statusTanda;
    
    public SequentialSearchPanel()
    {
        statusTanda=false;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        setSize(700,230);
        
        g.setColor(Color.BLACK);
        g.drawString(kalimat,10,200);
        g.setColor(new Color(153,153,255));
        
        /*gambar pointer high,low,dan mid*/
        if(statusTanda==true)
            {
            gambarTanda(node[index].getPosKotakX()+10,node[index].getPosKotakY() +40,
                    node[index].getPosKotakX()+15,node[index].getPosKotakY()+35,
                    node[index].getPosKotakX()+20,node[index].getPosKotakY()+40,"pointer",g);
            }
        
        for(int a=0;a<n;a++)
        {
            g.setColor(node[a].getWarna());
            g.fillRect(node[a].getPosKotakX(),node[a].getPosKotakY(),30,30);
            g.setColor(Color.BLACK);
            g.drawString(node[a].getAngka()+"",node[a].getPosAngkaX(),node[a].getPosAngkaY());
        }  
        
        if(pointer>0)
        {
            showPointer(g);
            g.setColor(Color.BLACK);
            g.drawString("void sequentialSearch()",370,10);
            g.drawString("{",370,25);
            g.drawString("int a,cek=0;",370,40);
            g.drawString("for(a=0;a<n;a++)",370,55);
            g.drawString("{",370,70);
            g.drawString("if(arr[a] == key)",370,85);
            g.drawString("{cek=1;break;}",370,100);
            g.drawString("}",370,115);
            g.drawString("if(cek==1)",370,130);
            g.drawString("{print(\"ditemukan\");}",370,145);
            g.drawString("else",370,160);
            g.drawString("{print(\"tdk ketemu\");}",370,175);
            g.drawString("}",370,190);
        }
    }
    
    public void showPointer(Graphics g)
    {
        int arrayX[]={355,365,355};
        int arrayY[]={pointer-5,pointer,pointer+5};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
    }
    
    public void gambarTanda(int x1, int y1,int x2, int y2, int x3, int y3, String kata, Graphics g)
    {
        /*bentuk segitiga 1=kiri bawah, 2=atas, 3=kanan bawah*/
        int arrayX[]={x1,x2,x3};
        int arrayY[]={y1,y2,y3};
        g.setColor(new Color(153,153,255));
        g.fillPolygon(arrayX,arrayY,3);
        g.setColor(Color.BLUE);
        g.drawString(kata,x1-10,y1+10);
    }
    
    public void setNode(Node node,int index)
    {
        this.node[index] = node;
    }
    
    public void setN(int n)
    {
        this.n=n;
    }
    
    public void setKalimat(String kal)
    {
        this.kalimat=kal;
    }
    
    public void setKey(int key)
    {
        this.key=key;
    }
    
    public void setIndex(int index)
    {
        this.index=index;
    }
    
    public void setStatusTanda(boolean statusTanda)
    {
        this.statusTanda=statusTanda;
    }
    
    public void setPointer(int pointer)
    {
        this.pointer=pointer;
    }
    
    public Node getNode(int index)
    {
        return node[index];
    }
    
    public int getN()
    {
        return n;
    }
    
    public String getKalimat()
    {
        return kalimat;
    }
    
    public int getKey()
    {
        return key;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public boolean getStatusTanda()
    {
        return statusTanda; 
    }
    
    public int getPointer()
    {
        return pointer;
    }
}

class SequentialSearchFrame extends JFrame
{
    private JButton input = new JButton("Input");
    private JButton search = new JButton("Search");
    private JButton reset = new JButton("Reset");
    private JButton keyword =  new JButton("Keyword");
    private JButton help = new JButton("Help");
    private SequentialSearchPanel panel = new SequentialSearchPanel();
    private JPanel buttonPanel = new JPanel();
    private BorderLayout tampilan = new BorderLayout();
    private Node node[] = new Node[6];
    private int n;
    private int key;
    private boolean statusInput;
    private boolean statusSearch;
    private boolean statusKeyword;
    /*untuk status true=siap diexecute, false=sedang dijalankan*/
    
    public SequentialSearchFrame()
    {
        super("Simulasi Sequential Search");
        statusInput=true;
        statusSearch=true;
        statusKeyword=true;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        input.setToolTipText("Input data");
        search.setToolTipText("Search data");
        reset.setToolTipText("Hapus semua data");
        keyword.setToolTipText("Keyword data");
        help.setToolTipText("Petunjuk penggunaan");
        setLayout(tampilan);
        
        add(panel,BorderLayout.NORTH);
            
        input.addActionListener(new h());
        search.addActionListener(new h());
        reset.addActionListener(new h());
        keyword.addActionListener(new h());
        help.addActionListener(new h());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(input);
        buttonPanel.add(keyword);
        buttonPanel.add(search);
        buttonPanel.add(reset);
        buttonPanel.add(help);
        add(buttonPanel,BorderLayout.SOUTH);
    }
    
    public void inputData()
    { 
        /*validasi banyak angka*/
        do{
            String a=JOptionPane.showInputDialog(null,"Masukkan banyak angka [1-6]","Jumlah Bilangan",JOptionPane.PLAIN_MESSAGE);    
            try{
                n=Integer.parseInt(a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);        
            }
        
        if(n>=1 && n<=6)
            {break;}
        }while(true);
        
        /*validasi input angka*/
        for(int c=1;c<=n;c++)
        {
            int x = 0;
            do{
                String b=JOptionPane.showInputDialog(null,"Masukan angka ke-"+c+" [1-99]","Input",JOptionPane.PLAIN_MESSAGE);
                try
                    {
                    x=Integer.parseInt(b);
                    }
                catch(Exception e)
                    {
                    JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);      
                    }
            if(x>=1 && x<=99)
                {break;}
            }while(true);
                  
            node[c-1].setAngka(x);
            node[c-1].setPosAngkaX(20+ 35*(c-1));
            node[c-1].setPosAngkaY(70);
            node[c-1].setPosKotakX(10+ 35*(c-1));
            node[c-1].setPosKotakY(50);       
        }
        
        sort();
        /*status jadi false supaya tdk bisa input ulang*/
        statusInput=false;
    }
    
    public void sort()
    {
        for(int a=n-2;a>=0;a--)
        {
            for(int b=0;b<=a;b++)
            {
                if(node[b].getAngka()>node[b+1].getAngka())
                {
                    int temp=node[b].getAngka();
                    node[b].setAngka(node[b+1].getAngka());
                    node[b+1].setAngka(temp);
                }
            }
        }
    }
    
    public void sequentialSearch()
    {
        int a,b,cek=0;
        statusSearch=false;

        /*inisialisasi panel*/
        panel.setN(n);
        panel.setKey(key);
        panel.setKalimat("key = "+key);
        panel.setPointer(35);
        for(a=0;a<n;a++)
        {
            panel.setNode(node[a],a);
        }
        panel.repaint();repaint();
        
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        
        panel.setStatusTanda(true);
        panel.setIndex(0);
        panel.setPointer(50);
        panel.repaint();repaint();
        
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        
        for(a=0;a<n;a++)
        {
            panel.setIndex(a);
            panel.setPointer(80);
            panel.repaint();repaint();
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        
            if(node[a].getAngka()==key)
            {cek=1;break;}
        }
        
        if(cek==1)
        {
            panel.setKalimat("key = "+key+" ditemukan");
            panel.setPointer(125);
            panel.repaint();repaint();
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            panel.setKalimat("key = "+key+" tidak ditemukan");
            panel.setPointer(170);
            panel.repaint();repaint();
            /*pemberitahuan*/
            JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        }
        
        panel.setPointer(185);
        panel.setKalimat("Sequential search selesai");
        panel.repaint();repaint();
        
        /*pemberitahuan*/
        JOptionPane.showMessageDialog(null,"Lanjut?","Pemberitahuan",JOptionPane.INFORMATION_MESSAGE);
        statusSearch=true;
    }
    
    public void hapusData()
    {
        statusInput=true;
        statusSearch=true;
        statusKeyword=true;
        panel.setStatusTanda(false);
        n=0;
        key=0;
        node[0]=new Node();
        node[1]=new Node();
        node[2]=new Node();
        node[3]=new Node();
        node[4]=new Node();
        node[5]=new Node();
        panel.setN(n);
        panel.setKey(key);
        panel.setBackground(Color.WHITE);
        panel.setKalimat("");
        panel.setPointer(0);
        panel.repaint();
        repaint();
    }
    
    public void inputKey()
    {
        String a;
        do{
            a=JOptionPane.showInputDialog(null,"Masukkan angka yang dicari [1-99]","Keyword",JOptionPane.PLAIN_MESSAGE);
            try
            {
                key=Integer.parseInt(a);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Bukan angka!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);
            }
        if(key>=1 && key<=99)
            {break;}
        }while(true);  
        statusKeyword=false;
    }
    
    public void tampilPetunjuk()
    {
        Petunjuk show=new Petunjuk();
    }
    
    private class h implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==input)
            {
                if(statusInput==true)
                    {inputData();}
                else
                    {JOptionPane.showMessageDialog(null,"Sudah diinput!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }
            
            if(e.getSource()==search)
            {
                if(statusSearch==true)
                    {
                    if(statusInput==false && statusKeyword==false)
                        {
                        sequentialSearch();
                        }
                    else
                        {JOptionPane.showMessageDialog(null,"Belum input data atau keyword!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
                    }
                else
                    {JOptionPane.showMessageDialog(null,"Sedang disearch!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }
            
            if(e.getSource()==reset)
            {
                hapusData();
            }
            
            if(e.getSource()==keyword)
            {
                if(statusKeyword==true)
                    {inputKey();}
                else
                    {JOptionPane.showMessageDialog(null,"Sudah diinput!","Pesan Kesalahan",JOptionPane.ERROR_MESSAGE);}
            }
            
            if(e.getSource()==help)
            {
                tampilPetunjuk();
            }
        }
    }
}

public class SequentialSearchSimulation
{
    public static void main(String args[])
    {
        SequentialSearchFrame sequential = new SequentialSearchFrame();
        sequential.setSize(700,300);
        sequential.setVisible(true);
        sequential.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

