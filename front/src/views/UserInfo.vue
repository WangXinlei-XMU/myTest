<template>
  <div class="info">
    <div class="block">
      <div class="name" >
        <el-avatar :size="100" v-if="user.avatarUrl" :src="user.avatarUrl"></el-avatar>
        <el-avatar :size="100" v-else src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      </div>
      <p class="name">{{user.name}}</p>
      <p class="introduce" >个人简介:{{user.introduce}}</p>
    </div>
    <el-tabs>
      <el-tab-pane label="文章" name="first">
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
                <div class="bottom clearfix">
                  <time class="time">创造时间：{{ article.createTime }}</time>
                </div>
                <div>
                  <span>标签：</span>
                  <span v-if="!article.labels||article.labels.length==0"><el-tag>无标签</el-tag></span>
                  <span class="article-label" v-for="(label,i) in article.labels" v-bind:key="i">
                      <el-tag>{{ label }}</el-tag>
                    </span>
                </div>
              </div>
            </el-card>
          </el-col>
        </div>
      </el-tab-pane>
      <el-tab-pane label="个人信息" name="second">
        <p>邮箱：{{user.email}}</p>
        <p>电话：{{user.phone}}</p>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>

import utils from "../../utils/utils";

export default {
  name: 'Info',

  data()
  {
    return{
      id:1,
      msg:{
        size:5,
        current:1,
        total:0,
      },
      user:{},
      lists:[],
      token:1
    }
  },
  created() {
    this.id=Number(this.$route.query.userId);
    this.token=localStorage.getItem("token");
    this.getUser();
    this.getArticle();
  },
  methods:{
    getUser(){
      let success=(response)=>{
        this.user=response.data.obj;
      }
      utils.axiosMethod({
        method:"Get",
        url:"/user/"+this.id+"/",
        callback:success
      })
    },
    getArticle(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/user/"+this.id+"/",
        data:this.msg,
        callback:success
      })
    },
    //页码改变
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.msg.size=val;
      this.getArticle();
    },
    //页码改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.msg.current=val;
      this.getArticle();
    },
    //前往文章页面
    gotoArticle(index){
      this.articleId=this.lists[index].id;
      this.$router.push('/info?artId='+this.articleId);
    },
  }
}
</script>


<style scoped>

.info .name{
  text-align:center;
  font-size: 30px;
  color: rgb(0, 0, 0);
}
.info .introduce{
  text-align: left;
  font-size: 12px;
  color: #555;
}

.info p{
  text-align: left;
}

.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
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

