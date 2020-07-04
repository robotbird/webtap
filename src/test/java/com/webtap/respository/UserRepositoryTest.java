package com.webtap.respository;

import com.webtap.domain.entity.*;
import com.webtap.domain.view.UserVO;
import com.webtap.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
  private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void  testRolesByUserId(){
	    List<Role> roles = roleRepository.findRolesByUserId(1L);
        List<Role> admins =  roles.stream().filter(role -> role.getName().equals("admin")).collect(Collectors.toList());
        if(admins.size()>0){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testUsers(){
		List<User> list = userRepository.findAllByRoleId(1L);
		for(Object user:list){
			User user1 =(User)user;
		}
	}

	@Test
    public void findObjectsByOrgId(){
	    //u.id,u.user_name,u.email,u.create_time,u.pass_word,u.org_id,group_concat(r.description) as strRole
	    List<Object []> objects = userRepository.findObjectsByOrgId(1L);
	    List<User> userList = objects.stream().map(User::new).collect(Collectors.toList());
	    userList.size();
    }

	@Test
	public void testfindAllByOrgId(){
		List<User> list = userRepository.findAllByOrgId3(1L);


		list.size();

		//User2 user2 = new User2();
		//user2 = get(User2.class,list.get(0));


		//System.out.print(user2.getId());
		//List<User2> userList = new ArrayList<>();

       // List<User2> list2 = new ArrayList<User2>((Collection<? extends User2>) Arrays.asList(list));

		//List<Object> user2List = Arrays.asList(list);
		//Collections.addAll(userList, list.toArray(new User2[list.size()]));
		//list2.size();
	}



	@Test
	public void testfindAllByOrgId2() throws Exception {
        List<User> objects = userRepository.findAllByOrgId(1L);


    }


    public static List<Map<String, Object>> getKeysAndValues(List<Object> object) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Object obj : object) {
            Class userCla;
            // 得到类对象
            userCla = (Class) obj.getClass();
            /* 得到类中的所有属性集合 */
            Field[] fs = userCla.getDeclaredFields();
            Map<String, Object> listChild = new HashMap<String, Object>();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                Object val = new Object();
                try {
                    val = f.get(obj);
                    // 得到此属性的值
                    listChild.put(f.getName(), val);// 设置键值
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.add(listChild);// 将map加入到list集合中
        }
        System.out.println("多个（列表）对象的所有键值====" + list.toString());
        return list;
    }


    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /*
             * String type = f.getType().toString();//得到此属性的类型 if
             * (type.endsWith("String")) {
             * System.out.println(f.getType()+"\t是String"); f.set(obj,"12") ;
             * //给属性设值 }else if(type.endsWith("int") ||
             * type.endsWith("Integer")){
             * System.out.println(f.getType()+"\t是int"); f.set(obj,12) ; //给属性设值
             * }else{ System.out.println(f.getType()+"\t"); }
             */

        }
        System.out.println("单个对象的所有键值==反射==" + map.toString());
        return map;
    }
    public static  <T> T get(Class<T> clz,Object o){
        if(clz.isInstance(o)){
            return clz.cast(o);
        }
        return null;
    }


    public static <T> List<T> objectToBean(List<Object[]> objList, Class<T> clz) throws Exception{
        if (objList==null || objList.size()==0) {
            return null;
        }

        Class<?>[] cz = null;
        Constructor<?>[] cons = clz.getConstructors();


        for (Constructor<?> ct : cons) {
            Class<?>[] clazz = ct.getParameterTypes();
            if (objList.get(0).length == clazz.length) {
                cz = clazz;
                break;
            }
        }

        List<T> list = new ArrayList<T>();
        for (Object[] obj : objList) {
            Constructor<T> cr = clz.getConstructor(cz);
            User2 user2 = (User2) cr.newInstance(obj);

            user2.getId();
            //System.out.println(user2.getId());

            list.add(cr.newInstance(obj));
        }
        return list;
    }

//	@Test
//	public void testfindAllByOrgId2(){
//		List<UserVO> list = userRepository.findAllByOrgId2(1L);
//
//		for(UserVO user:list){
//			UserVO user1 =user;
//		}
//	}

}