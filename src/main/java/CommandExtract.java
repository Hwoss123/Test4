import Command.Command;
import Io.MultiWriteHandler;

import java.util.ArrayList;
import java.util.List;

public class CommandExtract {
//    执行把输入的东西解析
    public Command Extract(String userInput)  {
        String[] split = userInput.split("说：");
        return Command(split);
//        注意返回的指令对象可能不存在，也就是错误的指令的时候
    }

    public Command Command (String[] split) {
        List<String> argList = new ArrayList<>();
        String[] s = split[1].split(" ");
        String newString = s[0].trim();
        String[] strings =split[1].split("\\s+");
//        这里从一开始保证命令不进去
        for (int i = 1; i < strings.length; i++) {
            argList.add(strings[i]);
        }
        newString = newString.toUpperCase();
        newString = "Command."+newString+"Command";
        Class<?> aClass = null;
        try {
            aClass = Class.forName(newString);
            if(Command.class.isAssignableFrom(aClass)){
                try {
//        如果是有效的命令，那么解析来就是创建对象并且传入参数
                    Command command = (Command) aClass.getDeclaredConstructor().newInstance();
                    command.setArgs(argList);
                    return command;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            MultiWriteHandler.setClient("输入的命令错误");
        }
        return null;
    }
}
