package com.dhcc.citic.cloud;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dhcc.citic.cloud.model.SysUser;


@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootDemoApplicationTests {

	
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Ignore
	@Test
    public void set(){
        redisTemplate.opsForValue().set("test:set","testValue1");
    }
	
	@Ignore
	@Test
	public void testSet(){
		SysUser user1 = new SysUser();
		user1.setUserId(1);
		SysUser user2 =new SysUser();
		user2.setUserId(1);
		Set<SysUser> users = new HashSet<SysUser>();
		users.add(user1);
		users.add(user2);
		System.out.println("---------------------user1 hashcode="+user1.hashCode());
		System.out.println("---------------------user2 hashcode="+user2.hashCode());
		System.out.println("---------------------(user1=user2) is "+user1.equals(user2));
		System.out.println("---------------------users size="+users.size());
		Assert.assertTrue(users.size()==1);
	}

}
