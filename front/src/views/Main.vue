<template>
  <div>

    <div class="header">
      <h1>欢迎来到Blog</h1>
      <p>创造自己的精彩</p>
    </div>

    <div>
      <el-tabs :tab-position="tabPosition" style="height: 200px;">
        <el-tab-pane label="文章">
<!--          搜索框-->
          <div class="navbar">
            <label class="labal">文章搜索</label>
            <el-input class="input"
                      placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model.trim="msg.search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchBtn">搜索</el-button>
          </div>
<!--          限制条件-->
          <el-collapse>
            <el-collapse-item title="搜索限制" name="1">
              <el-form ref="form" label-width="80px">
                <el-form-item label="作者">
                  <el-input v-model.trim="msg.user"></el-input>
                </el-form-item>
                <el-form-item label="标题">
                  <el-input v-model.trim="msg.title"></el-input>
                </el-form-item>
                <el-form-item label="发布时间">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model.trim="msg.date1" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model.trim="msg.date2" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-collapse-item>
          </el-collapse>
<!--          页码-->
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="msg.current"
              :page-sizes="[5, 10, 20]"
              :page-size="msg.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="msg.total">
            </el-pagination>
          </div>
<!--          显示-->
          <div>
            <li v-if="lists.length===0">
              <el-empty description="没有搜到有效信息"></el-empty>
            </li>
            <el-col :span="8" v-for="(article,index) in lists" v-bind:key="index">
              <el-card :body-style="{ padding: '10px' }">
                <img v-if="article.backUrl" :src="article.backUrl" class="image"/>
                <img v-else src="https://pic2.zhimg.com/v2-0cc642bd1a977891d2c3407ff2f55619_r.jpg" class="image"/>
                <div style="padding: 14px;">
                  <span class="article-user" @click="gotoArticle(index)">文章标题：{{ article.title }}</span>
                  <div>
                    <span class="article-user" @click="gotoUser(index)">作者：{{ article.user }}</span>
                  </div>
                  <div class="bottom clearfix">
                    <time class="time">创造时间：{{ article.createTime }}</time>
                  </div>
                  <div>
                    <span>标签：</span>
                    <span v-if="article.labels.length===0"><el-tag>无标签</el-tag></span>
                    <span class="article-label" v-for="(label,i) in article.labels" v-bind:key="i">
                      <el-tag>{{ label }}</el-tag>
                    </span>
                  </div>
                </div>
              </el-card>
            </el-col>
          </div>
        </el-tab-pane>
        <el-tab-pane label="用户">
          <!--          搜索框-->
          <div class="navbar">
            <label class="labal">用户搜索</label>
            <el-input class="input"
                      placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model.trim="user.search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchUser">搜索</el-button>
          </div>

          <!--          页码-->
          <div class="block">
            <el-pagination
              @size-change="handleSizeChangeUser"
              @current-change="handleCurrentChangeUser"
              :current-page="user.current"
              :page-sizes="[5, 10, 20]"
              :page-size="user.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="user.total">
            </el-pagination>
          </div>


          <li v-if="lists.length===0">
            <el-empty description="没有搜到有效信息"></el-empty>
          </li>
          <!--        显示-->
          <li v-for="(user,index) in userLists" v-bind:key="index">
            <p @click="gotoUser(index)">
              <el-avatar v-if="user.avatarUrl!==null" :size="50" :src="user.avatarUrl"></el-avatar>
              <el-avatar v-else :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
              <span class="comName">{{user.name}}</span>
            </p>
          </li>
        </el-tab-pane>
        <el-tab-pane v-if="token!==null" label="个人主页">
          <label @click="gotoMe">我的主页</label>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>

import utils from "../../utils/utils";

export default {
  name: 'Main',

  data()
  {
    return{
      tabPosition:'top',
      msg:{
        search:"",
        size:5,
        current:1,
        total:0,
        user:"",
        title:'',
        date1:'',
        date2:''
      },
      user:{
        search:"",
        size:5,
        current:1,
        total:0,
      },
      articleId:-1,
      userId:-1,
      lists:[],
      userLists:[],
      token:-1
    }
  },
  created() {
    this.searchBtn();
    this.searchUser();
    this.token=localStorage.getItem("token");
  },
  methods:{
    //搜索
    searchBtn(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/search/",
        data:this.msg,
        callback:success
      })
    },
    //限制条件搜索
    onSubmit(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/limit/",
        data:this.msg,
        callback:success
      })
    },
    //前往文章页面
    gotoArticle(index){
      this.articleId=this.lists[index].id;
      this.$router.push('/info?artId='+this.articleId);
    },
    //前往作者页面
    gotoUser(index){
      this.userId=this.lists[index].userId;
      this.$router.push('/userInfo?userId='+this.userId);
    },
    //文章页码改变
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.msg.size=val;
      this.msg.current=1;
      this.searchBtn();
    },
    //文章页码改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.msg.current=val;
      this.searchBtn();
    },
    //用户页码改变
    handleSizeChangeUser(val) {
      console.log(`每页 ${val} 条`);
      this.user.size=val;
      this.user.current=1;
      this.searchUser();
    },
    //用户页码改变
    handleCurrentChangeUser(val) {
      console.log(`当前页: ${val}`);
      this.user.current=val;
      this.searchBtn();
    },
    //查询用户
    searchUser(){
      let success=(response)=>{
        this.userLists=response.data.list;
        this.user.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/user/search",
        data:this.user,
        callback:success
      })
    },
    //前往自己的主页
    gotoMe(){
      if(this.token!==null)
        this.$router.push('/me');
      else
        this.$message.error('请先登录！');
    }
  }
}
</script>
<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

</style>
