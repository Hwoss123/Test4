package Command;


import HashMapControl.SHHashMap;
import HashMapControl.SLHashMap;
import HashMapControl.SSHashMap;
import Io.MultiWriteHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SAVECommand implements Command {
//手动强制将缓存在类里面的数据执行写入
    @Override
    public void setArgs(List<String> list) {

    }

    @Override
    public void execute() {
        try {
            HashMap<String, String> hm = SSHashMap.input();
            HashMap<String, LinkedList<String>> hml = SLHashMap.input();
            HashMap<String, HashMap<String, String>> hmh = SHHashMap.getSHHashMap();
            SSHashMap.output(hm);
            SLHashMap.output(hml);
            SHHashMap.output(hmh);
            MultiWriteHandler.setClient("保存成功");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            MultiWriteHandler.setClient("保存失败");
        }


    }
}
