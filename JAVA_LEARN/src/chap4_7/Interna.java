package chap4_7;

import java.util.Locale;
import java.util.ResourceBundle;

public class Interna {
 
    public static void main(String[] args) {
        //��Դ������(����+myproperties)
        String basename = "myexample";
        //�������Ի���
        Locale cn = Locale.CHINA;//����
        Locale us = Locale.US;//Ӣ��
        //���ݻ��������Ի������ض�Ӧ��������Դ�ļ�
        ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename,cn);//����myproperties_zh.properties
        ResourceBundle myResourcesUS = ResourceBundle.getBundle(basename,us);//����myproperties_en.properties
         
        //������Դ�ļ��� ����Ϳ��Ե���ResourceBundleʵ������� getString������ȡָ������Դ��Ϣ��������Ӧ��ֵ��
        //String value =  myResources.getString(��key");
        String GDUTCN = myResourcesCN.getString("school");
         
        String GDUTUS = myResourcesUS.getString("school");
         
        System.out.println(GDUTCN);
        System.out.println(GDUTUS);
    }
}