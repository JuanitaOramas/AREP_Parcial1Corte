package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class HttpServer {

    public static void main(String[] args ) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }

        boolean running  = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean flagforpath = true;
            String path ="";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if (flagforpath){
                    flagforpath = false;
                    path = inputLine.split(" ")[1];
                } else{
                    if (!in.ready()) {break;}
                }
            }


            outputLine = "";
            System.out.println(path);

            if (path.contains("/chat")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n" + "\r\n"
                        + HtmlWithForms();
            } else if (path.contains("/chat2")) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/json\r\n"
                        + "\r\n";

            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();

        }

        serverSocket.close();


    }

    public static String getterFields(Class c){
            String response = "";
            Field[] result = c.getDeclaredFields();
            for (Field f: result){
                response = response + f.getName();
            }

        return response;
    }

    public static String interpreteCommand(Class c) throws NoSuchMethodException {
        if (tipo (String.valueOf(c)) != null){
            c.getterFields(c);
        }
        c.getDeclaredMethod("", null);

        return "";

    }

    //saber tipo
    public static Class<String> tipo (String s)  {
        if (s == "String"){
            return String.class;
        }
        return null;
    }


    //methods
    public Method[] getDeclaredMethodos(Class c) throws NoSuchMethodException {
        String response = "";
        Method metodos = c.getDeclaredMethod("chat", null);
        ArrayList<>;
        for (Method m: metodos){
            response = response + m.toString();
        }
        return new Method[0];
    }
    public static String withivoke(){
        return "withivoke";
    }









    public static String HtmlWithForms(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Form Example</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Fisrt form</h1>\n" +
                "<form action=\"/consulta\">\n" +
                "    <label for=\"name\">Name:</label><br>\n" +
                "    <input type=\"text\" id=\"comando\" name=\"comando\" value=\"John\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"comando\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "<h1>Form with POST</h1>\n" +
                "<form action=\"/hellopost\">\n" +
                "    <label for=\"postname\">Name:</label><br>\n" +
                "    <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n" +
                "    <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n" +
                "</form>\n" +
                "\n" +
                "<div id=\"postrespmsg\"></div>\n" +
                "\n" +
                "<script>\n" +
                "            function loadPostMsg(name){\n" +
                "                let url = \"/hellopost?name=\" + name.value;\n" +
                "\n" +
                "                fetch (url, {method: 'POST'})\n" +
                "                    .then(x => x.text())\n" +
                "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n" +
                "            }\n" +
                "        </script>\n" +
                "</body>\n" +
                "</html>";
    }



}
