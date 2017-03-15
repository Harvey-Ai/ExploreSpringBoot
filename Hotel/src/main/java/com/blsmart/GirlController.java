package com.blsmart;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by charspan on 15/03/2017.
 */
@RestController
@RequestMapping(value = "/girls"/*, method = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE}*/)
public class GirlController {
    /**
     * RESTful API 设计
     * |请求类型|请求路径|功能
     * |GET|/girls|获取女生列表|
     * |POST|/girls|创建一个女生|
     * |GET|/girls/id|通过 id 查询一个女生|
     * |PUT|/girls/id|通过 id 更新一个女生|
     * |DELETE|girls/id|通过 id 删除一个女生|
     */
    //一般情况不能再这里调用 dao
    @Autowired
    private GirlRespository girlRespository;

    @GetMapping("")
    public List<Girl> girlList() {
        return girlRespository.findAll();
    }

    @PostMapping("")
    public Girl girlAdd(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setName(name);
        girl.setAge(age);
        return girlRespository.save(girl);
    }

    @GetMapping(value = "/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRespository.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        return girlRespository.save(girl);
    }

    @DeleteMapping(value = "/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRespository.delete(id);
    }

    //通过年龄查询
    @GetMapping(value = "/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRespository.findByAge(age);
    }

    @Autowired
    private GirlService girlService;

    @PostMapping(value = "/two")
    public void girlTwo() {
        girlService.insertTwo();
    }

}