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
        String name = request.getParameter("name");
        String cid = request.getParameter("cid");
        try {
            String sql = "select * from d_weather where 1=1  ";
            if(!StringUtils.isEmpty(name))
                sql += " and cname='"+name+"' ";
            if(!StringUtils.isEmpty(cid))
                sql += " and cid='"+cid+"' ";
            sql += " order by create_time desc limit 1 ";
            List<Map<String,Object>> list = dao.findBySqlToMap(sql);
            printJson(ResultUtil.success(list),response);
        }catch (Exception e){
            e.printStackTrace();
            printJson(ResultUtil.error(-1,e.getMessage()),response);
        }
    }
}
