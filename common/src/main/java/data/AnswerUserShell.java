package data;

import java.io.Serializable;

public class AnswerUserShell implements Serializable {
    private boolean flag = false;
    private String info = null;

    public AnswerUserShell(boolean flag, String info) {
        this.flag = flag;
        this.info = info;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getInfo() {
        return info;
    }
}
