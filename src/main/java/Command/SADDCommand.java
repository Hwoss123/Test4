package Command;

import HashMapControl.HashsetMap;
import Io.MultiWriteHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SADDCommand implements Command{
    private List<String> setArgs;

    public SADDCommand() {
    }

    @Override
    public void setArgs(List<String> list) {
        this.setArgs = list;
    }
    public SADDCommand(List<String> setArgs) {
        this.setArgs = setArgs;
    }
    @Override
    public void execute() {
        System.out.println("此时运行的是sadd命令");
        if(setArgs.size()==0){
            MultiWriteHandler.setClient("至少需要两个参数");
            return;
        }
        HashMap<String, HashSet<String>> hms = HashsetMap.getSetMap();
        String key = setArgs.get(0);
//        有个思路就是在原来的参数数组里面去掉第一个数组就可以了剩下作为参数
         setArgs.remove(0);
        HashSet<String> hs = new HashSet<String>(setArgs);
        hms.put(key, hs);
        HashsetMap.setHms(hms);
        MultiWriteHandler.setClient("1");
    }
}
