package com.jone.joneapi.task;

import com.alibaba.fastjson.JSONObject;
import com.jone.joneapi.dao.IDao;
import com.jone.joneapi.dao.weather.WeatherCodeRepository;
import com.jone.joneapi.dao.weather.WeatherRepository;
import com.jone.joneapi.entity.Weather;
import com.jone.joneapi.entity.WeatherCode;
import com.jone.joneapi.util.HttpUtil;
import com.jone.joneapi.util.WeatherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class WeatherTask {

    private static final Logger LOGGER =  LoggerFactory.getLogger(WeatherTask.class);

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherCodeRepository weatherCodeRepository;
    @Autowired
    private IDao dao;

    public void sayHello(){
        WeatherUtil demo = new WeatherUtil();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String url = "";
        try {
            List<WeatherCode> wcs = weatherCodeRepository.findByProvince("贵州");
            for (int i=0;i<wcs.size();i++) {
                WeatherCode wc = wcs.get(i);
                url = demo.generateGetNowWeatherURL(
                        wc.getCid(),
                        "zh-Hans",
                        "c"
                );
                //System.out.println("URL:" + url);
                String ret = HttpUtil.doGet(url);
                //System.out.println("ret:" + ret);
                JSONObject jsonObject= JSONObject.parseObject(ret);
                try {
                    jsonObject = jsonObject.getJSONArray("results").getJSONObject(0);
                }catch (Exception e){
                    continue;
                }
                List<Map<String,Object>> list = dao.findBySqlToMap("select * from d_weather where cid='"+wc.getCid()+"' order by create_time desc limit 1");
                if(list!=null&&list.size()>0){
                    Integer _temperature = Integer.parseInt(list.get(0).get("temperature").toString());
                    if(_temperature == jsonObject.getJSONObject("now").getInteger("temperature"))
                        continue;
                }
                Weather weather = new Weather();
                weather.setCid(jsonObject.getJSONObject("location").getString("id"));
                weather.setCname(jsonObject.getJSONObject("location").getString("name"));
                weather.setText(jsonObject.getJSONObject("now").getString("text"));
                weather.setCode(jsonObject.getJSONObject("now").getInteger("code"));
                weather.setTemperature(jsonObject.getJSONObject("now").getInteger("temperature"));
                weather.setCreateTime(new Date());
                Date  date = df.parse(jsonObject.getString("last_update").substring(0,10));
                weather.setUpdateTime(date);

                weatherRepository.save(weather);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception:" + e);
        }
    }
}
