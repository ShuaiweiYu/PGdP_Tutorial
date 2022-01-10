package demo;

import java.io.*;
import java.util.Date;

public class MyIO {
    private static File getFile(String path) {
        //通过路径来实例化一个File文件
        return new File(path);
    }

    public static void showFile(String path) {
        File file = getFile(path);
        //判断该文件是否存在
        if (file.exists()) {
            //获取文件绝对路径
            System.out.println("The absolute path of this File is " + file.getAbsolutePath());
            //获取文件名
            System.out.println("The name of the File is \"" + file.getName() + "\"");
            //获取文件大小
            System.out.println("The space of the file is \"" + file.length() + " Byte\"");
            //获取文件上次修改时间
            Date date = new Date(file.lastModified());
            System.out.println("the file was last modified at \"" + date + "\"\n");
        } else {
            System.out.println("The File dosen't exist!\n");
        }

    }

    //Java中，数据的输入和输出都是通过stream的方式来实现的
    //字节流 -> 8bit   InputStream/OutputStream    图片，视频等非文本数据
    //字符流 -> 16bit  Reader/Writer               文本文件
    //字节(Byte)是计量单位，表示数据量多少，是计算机信息技术用于计量存储容量的一种计量单位，通常情况下一字节等于八位。
    //字符(Character)计算机中使用的字母、数字、字和符号，比如'A'、'B'、'$'、'&'等。

    public static String reader(String path) {
        StringBuilder sb = new StringBuilder();
        File file = getFile(path);
        //提供字符输入流
        try (FileReader fr = new FileReader(file)) {
            //如果文件不大的话
            if (file.length() < 100) {
                int data;
                //读数据，当数据是-1的时候终止读取
                while ((data = fr.read()) != -1) {
                    sb.append((char) data);
                }
            }
            //如果文件太大了
            else {
                char[] charBuffer = new char[10];
                int len;
                //read(char[] cbuf)返回每次读入cbuf的字符个数，如果达到末尾流，则返回-1
                while ((len = fr.read(charBuffer)) != -1) {
                    sb.append((char[]) charBuffer);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出数据
        return sb.toString();
    }

    //节点流
    //FileInputStream/FileOutputStream/FileReader/FileWriter
    public static void writer(String path, boolean override, String message) {
        //提供字符输出流
        //override为false时(!override -> true)，不会覆盖原文件，而是在原文件末尾添加
        try (FileWriter fw = new FileWriter(getFile(path), !override)) {
            fw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyByte(String inputPath, String outputPath) {
        try (FileInputStream in = new FileInputStream(getFile(inputPath));
             FileOutputStream out = new FileOutputStream(getFile(outputPath))) {

            byte buffer[] = new byte[10];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //缓冲流
    //BufferedInputStream/BufferedOutputStream/BufferedReader/BufferedWriter
    //作用：提高流读取和写入的速度
    //缓冲流会自动flush() -> 刷新缓存区
    public static void copyByteBuf(String inputPath, String outputPath) {
        try (FileInputStream inputStream = new FileInputStream(getFile(inputPath));
             FileOutputStream outputStream = new FileOutputStream(getFile(outputPath));
             BufferedInputStream in = new BufferedInputStream(inputStream);
             BufferedOutputStream out = new BufferedOutputStream(outputStream)) {

            byte buffer[] = new byte[10];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readerBuf(String path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFile(path)))) {

            //老方法：
//            char[] charBuffer = new char[10];
//            int len;
//            while ((len = reader.read(charBuffer)) != -1) {
//                for (int i = 0; i < len; i++) {
//                    sb.append((char[]) charBuffer);
//                }
//            }

            //新方法
            String data;
            while ((data = reader.readLine()) != null) {
                sb.append(data);
                sb.append("\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void writerBuf(String path, boolean override, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFile(path), !override))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //转换流（属于字符流）
    //作用：提供字节与字符流之间的转换
    //字节流 -> InputStreamReader -> 字符流   （将字节的输入流转化为字符的输入流）
    //字符流 -> OutputStreamWriter -> 字节流  （将字符的输出流转化为字符的输出流）

    public static void trans(String path) {
        try (FileInputStream inputStream = new FileInputStream(getFile(path));
             //拿到字节流，使用对应的编码来解码
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8")) {

            char[] charBuffer = new char[10];
            int len;
            while ((len = inputStreamReader.read(charBuffer)) != -1) {
                String str = new String(charBuffer, 0, len);
                System.out.println(str);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打印流
    //字节打印流：printStream
    //字符打印流：printWriter
    //实现将基本数据类型的数据格式化为字符串输出


    public static void main(String[] args) {
//        showFile("src/textFile/hello.txt");
//        String info = reader("src/textFile/hello.txt");
//        writer("src/textFile/hello.txt", false, "I say: " + info);

//        long l1 = System.currentTimeMillis();
//        copyByte("src/textFile/v1.mp4", "src/textFile/v2.mp4");
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2 - l1);
//
//        System.out.println();
//
//        long l3 = System.currentTimeMillis();
//        copyByteBuf("src/textFile/v1.mp4", "src/textFile/v3.mp4");
//        long l4 = System.currentTimeMillis();
//        System.out.println(l4 - l3);


//        showFile("/Users/shuaiwei_yu/Desktop/Programm/IDEA_Java/experimentField/src/textFile/hello.txt");
//        String info = readerBuf("/Users/shuaiwei_yu/Desktop/Programm/IDEA_Java/experimentField/src/textFile/hello.txt");
//        writerBuf("/Users/shuaiwei_yu/Desktop/Programm/IDEA_Java/experimentField/src/textFile/hello.txt", true, info.toUpperCase());
//        showFile("/Users/shuaiwei_yu/Desktop/Programm/IDEA_Java/experimentField/src/textFile/hello.txt");
//
//        copyByteBuf("src/textFile/fish.jpg", "src/textFile/fish2.jpg");
    }

}
