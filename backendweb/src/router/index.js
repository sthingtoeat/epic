import { createRouter, createWebHistory } from 'vue-router'
import UserIndexView from '../views/user/UserIndexView'
import ErrorIndexView from '@/views/error/ErrorIndexView'
import LoginIndexView from '@/views/user/UserLoginView'
                                //@是指在src目录下
const routes = [
  {
    path:"/user",              //localhost:8080/user/
    name:"user_index_view",     //只是一个名字,可用于页面跳转router.push({name:"user_index_view"})
    component:UserIndexView,    //索引到对应的页面，而页面由上方导入
  },
  
  {
    path:"/login",
    name:"login_view",
    component:LoginIndexView,
  },

  {
    path:"/error",
    name:"error_view",
    component:ErrorIndexView,
  },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
