<template>
    <content-field>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <!---username ,password指的是v-model内的名称的值,prevent是防止提交以后刷新页面-->
                <form @submit.prevent="login(username , password)">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="输入用户名..">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="输入密码..">
                    </div>
                    <div class="error_message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-success">提交</button>
                </form>
            </div>
        </div>
    </content-field>
</template>
    
<script>
import $ from 'jquery'
import { ref } from 'vue'
import router from '../../router/index'
export default {

    setup() {
        let error_message=ref("");                      //ref使得变量error_message具有.value
           
        const login =(username , password) =>{

            $.ajax({
                url:"http://localhost:3000/test/login",
                method:"post",
                data:{
                    username:username,                  //冒号前面的是变量名，冒号后面的是函数传进来的参数
                    password:password,
                },
                success(resp){
                    if(resp.error_message === "success"){
                        localStorage.setItem("username" , username);
                        localStorage.setItem("userId" , resp.userId);
                        router.push({name:'user_index_view'})   //这里登录成功的话直接进行页面跳转
                    }
                    else
                    {
                        error_message.value = "用户名或密码错误";   
                    }   
                },
                error(){
                    error_message.value = "服务器异常";
                    console.log("服务器出现异常");
                    
                }   
            })
        }

        return {
            login,
            error_message,                              //返回是实时返回的，执行了login以后，就会更新error_message
        }                                               //这时候页面就能得到改变后的值
    },

    
}
</script>
<style scoped>
.error_message{
    color: red;
}
</style>