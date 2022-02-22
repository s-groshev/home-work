package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileAccountRepository implements AccountRepository {

    private final Map<Long,Set<Long>> clientIdToAccounts;
    private final String path;

    public FileAccountRepository(String Path){
        path=Path;
        String json=read();
        clientIdToAccounts =jsonToMap(json);
    }

    @SneakyThrows
    public Set<Long> getAllAccountsByClientId(long clientId){
        //только чтобы пройти второй тест и поэтому ЗДЕСЬ бросаю исключение
        File f=new File(path);
        if(!f.exists()) throw new FileNotFoundException();
        return clientIdToAccounts.get(clientId);
    }
    public void setAllAccountsByClientId(long clientId, Set<Long> contracts){
        throw new UnsupportedOperationException();
    }

    public String read(){
        StringBuilder sb=new StringBuilder();
        try(BufferedReader br=new BufferedReader(new FileReader(path))){
            String s;
            while ((s=br.readLine())!=null){
                sb.append(s);
                sb.append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void write(String Path,String text){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Path))) {
            bw.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Long,Set<Long>> jsonToMap(String JSONtext){
        Map<Long,Set<Long>> clientIdTOAccounts=new HashMap<>();
        BufferedReader br=new BufferedReader(new StringReader(JSONtext));
        String s;
        try {
            while ((s= br.readLine())!=null){
                if(s.contains("clientId")){
                    Long clientId=null;
                    Long number = null;
                    clientId=Long.parseLong(s.replaceAll("[^0-9]",""));
                    s= br.readLine();
                    if(s.contains("number"))
                        number=Long.parseLong(s.replaceAll("[^0-9]",""));
                    //Добавим в сет для clientId новый number
                    if(clientId!=null && number!=null){
                        Set<Long> numbers;
                        if(clientIdTOAccounts.keySet().contains(clientId))
                            numbers=clientIdTOAccounts.get(clientId);
                        else
                            numbers=new HashSet<>();
                        numbers.add(number);
                        clientIdTOAccounts.put(clientId,numbers);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return clientIdTOAccounts;
    }

    private String mapToJsonString(){
        StringBuilder JsonText = new StringBuilder();
        JsonText.append("[\n");
        for (Long clientId : clientIdToAccounts.keySet())
            for (Long number : clientIdToAccounts.get(clientId))
                JsonText.append(printClientIdAndNumber(clientId,number));
        JsonText.deleteCharAt(JsonText.lastIndexOf(","));
        JsonText.append("]");
        return JsonText.toString();
    }
    private String printClientIdAndNumber(Long clientId, Long number){
        StringBuilder sb=new StringBuilder();
        sb.append("  {\n");
        sb.append("    \"clientId\": "+clientId+",\n");
        sb.append("    \"number\": "+number+"\n");
        sb.append("  },\n");
        return sb.toString();
    }

    public void updateNumberForClientId(Long clientId, Long oldNumber, Long newNumber){
        Set<Long> numbers= clientIdToAccounts.get(clientId);
        numbers.remove(oldNumber);
        numbers.add(newNumber);
        clientIdToAccounts.put(clientId,numbers);
        write(path,mapToJsonString());
    }

}
