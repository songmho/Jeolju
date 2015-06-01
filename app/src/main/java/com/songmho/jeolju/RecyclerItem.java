package com.songmho.jeolju;

/**
 * Created by songmho on 2015-05-30.
 */
public class RecyclerItem {
    int beer_int,beer_money_int;
    int soju_int,soju_money_int;
    int cock_int,cock_money_int;
    int etc_int,etc_money_int;
    int sum;
    String date;

    public RecyclerItem(int b,int b_money, int s,int s_money,int c,int c_money,int e, int e_money,int sum,int year,int mon, int day ){
        this.beer_int=b;
        this.beer_money_int=b_money;
        this.soju_int=s;
        this.soju_money_int=s_money;
        this.cock_int=c;
        this.cock_money_int=c_money;
        this.etc_int=e;
        this.etc_money_int=e_money;
        this.sum=sum;
        this.date=year+"."+mon+"."+day;
    }

    public int getBeer_int(){
        return this.beer_int;
    }
    public int getBeer_money_int(){
        return this.beer_money_int;
    }
    public int getSoju_int(){
        return this.soju_int;
    }
    public int getSoju_money_int(){
        return this.soju_money_int;
    }
    public int getCock_int(){
        return this.cock_int;
    }
    public int getCock_money_int(){
        return this.cock_money_int;
    }
    public int getEtc_int(){
        return this.etc_int;
    }
    public int getEtc_money_int(){
        return this.etc_money_int;
    }

    public int getSum(){
        return this.sum;
    }
    public String getDate(){
        return this.date;
    }
}

