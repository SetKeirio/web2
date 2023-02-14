package beans;

import java.io.Serializable;

public class ResultBean implements Serializable{
    private boolean inArea;
    private String nowTime;
    private String executeTime;
    private double x, y, r;

    public ResultBean(boolean inArea, String nowTime, String executeTime, double x, double y, double r) {
        this.inArea = inArea;
        this.nowTime = nowTime;
        this.executeTime = executeTime;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public ResultBean(){
        this.inArea = false;
        this.nowTime = "";
        this.executeTime = "";
        this.x = 0.0;
        this.y = 0.0;
        this.r = 0.0;
    }

    public boolean isInArea(){
        return inArea;
    }

    public void setInArea(boolean inArea){
        this.inArea = inArea;
    }

    public double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getR(){
        return r;
    }

    public void setR(double r){
        this.r = r;
    }

    public String getNowTime(){
        return nowTime;
    }

    public void setNowTime(String nowTime){
        this.nowTime = nowTime;
    }

    public String getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(String executeTime){
        this.executeTime = executeTime;
    }
}
