package com.example.shop.controller;

import com.example.shop.entity.User;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    public static final String SECRET = "12345678901234567890123456789012"; // 32个字符

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userMapper.findById(id);
    }


    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody User user) {
        Map<String, Object> resp = new HashMap<>();

        int n= userService.register(user);
        if(n>0){
            resp.put("code", 200);
            resp.put("msg", "新增用户成功");

        }else {
            resp.put("code", 400);
        }
        return resp;
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.listAll();
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        // 调用 MyBatis 查询
        User user = userMapper.findByUsername(username);

        Map<String, Object> resp = new HashMap<>();

        if (user == null || !user.getPassword().equals(password)) {
            resp.put("code", 401);
            resp.put("msg", "账号或密码错误");
            return resp;
        }

        // 登录成功（返回token）
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))//七天
                .signWith(key)  // 不再需要指定算法
                .compact();

        resp.put("code", 200);
        resp.put("msg", "登录成功");
        resp.put("token", token);

        return resp;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody User user) {
        int n = userMapper.update(user);

        Map<String, Object> resp = new HashMap<>();
        if (n > 0) {
            resp.put("code", 200);
            resp.put("msg", "修改成功");
        } else {
            resp.put("code", 500);
            resp.put("msg", "修改失败");
        }
        return resp;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable int id) {
        int n = userMapper.delete(id);

        Map<String, Object> resp = new HashMap<>();
        if (n > 0) {
            resp.put("code", 200);
            resp.put("msg", "删除成功");
        } else {
            resp.put("code", 500);
            resp.put("msg", "删除失败");
        }
        return resp;
    }

    @PostMapping("/batchDelete")
    public Map<String, Object> batchDelete(@RequestBody List<Integer> ids) {
        int n = userMapper.batchDelete(ids);

        Map<String, Object> resp = new HashMap<>();
        if (n > 0) {
            resp.put("code", 200);
            resp.put("msg", "批量删除成功");
        } else {
            resp.put("code", 500);
            resp.put("msg", "批量删除失败");
        }
        return resp;
    }

    @GetMapping("/testSQL")
    public void testSql() {
        long t = System.currentTimeMillis();
        userMapper.findAll();
        System.out.println("SQL耗时：" + (System.currentTimeMillis() - t) + "ms");
    }



}
