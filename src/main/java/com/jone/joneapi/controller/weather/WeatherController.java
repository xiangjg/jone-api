package com.jone.joneapi.controller.weather;

import com.jone.joneapi.controller.BaseController;
import com.jone.joneapi.dao.IDao;
import com.jone.joneapi.entity.common.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "weather")
public class WeatherController extends BaseController {

    @Autowired
    private IDao dao;

    @RequestMapping(value = "query")
    public void query(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        try {
            if(StringUtils.isEmpty(cid)){
                printJson(ResultUtil.error(-1,"参数cid不能为空"),response);
            }else{
                String sql = "select * from d_weather where cid='"+cid+"' order by create_time desc limit 1";
                List<Map<String,Object>> list = dao.findBySqlToMap(sql);
                printJson(ResultUtil.success(list),response);
            }
        }catch (Exception e){
            e.printStackTrace();
            printJson(ResultUtil.error(-1,e.getMessage()),response);
        }
    }
}
