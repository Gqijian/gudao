package me.zj22.gudao.server.web.utils;

/**
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/5/8 14:26:31
 */
public class KdUtils {

    private final static Double PRICE_BASIS_CD = (double) 6;

    private final static Double PRICE_BASIS_ADD = (double) 5;

    private final static Double PRICE_BASIS = (double) 8;

    private final static Double PRICE_BASIS_BJ = (double) 10;

    private final static String CITY_BASIS = "成都市";

    private final static String CAPITAL = "北京市";

    public Double Kd(String location1, Integer numb){

        Integer num = null;
        Double weight = null;

        if(location1.equals(CITY_BASIS) && 0 < numb && numb <= 2){
            return PRICE_BASIS_CD;
        }else if (location1.equals(CITY_BASIS) && numb > 2) {
            num = numb - 2;
            weight = (double) (0.4 * num)-0.2;
            weight = Math.ceil(weight);
            return PRICE_BASIS_CD + weight * PRICE_BASIS_ADD;
        }else if (location1.equals(CAPITAL) && 0 < numb && numb <= 2) {
            return PRICE_BASIS_BJ;
        }else if (location1.equals(CAPITAL) && numb > 2) {
            num = numb - 2;
            weight = (double) (0.4 * num)-0.2;
            weight = Math.ceil(weight);
            return PRICE_BASIS_CD + weight * PRICE_BASIS_ADD;
        }else if (!location1.equals(CAPITAL) && !location1.equals(CITY_BASIS) &&  0 < numb && numb <= 2) {
            return PRICE_BASIS;
        }else if (!location1.equals(CAPITAL) && !location1.equals(CITY_BASIS) && numb > 2) {
            num = numb - 2;
            weight = (double) (0.4 * num)-0.2;
            weight = Math.ceil(weight);
            return PRICE_BASIS + weight * PRICE_BASIS_ADD;
        }
        return null;
    }
}
