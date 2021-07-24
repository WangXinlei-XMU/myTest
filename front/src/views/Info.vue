<template>
  <div class="info">
    <div class="block">
      <el-image :src="article.backUrl"></el-image>
      <p class="title" >{{article.title}}</p>
      <p class="user" >作者：{{article.user}}</p>
      <p class="time" >创造时间：{{article.createTime}}</p>
      <span class="label" >标签：</span>
      <span class="article-label" v-for="(label,i) in article.labels" v-bind:key="i">
        <el-tag>{{ label }}</el-tag>
      </span>
    </div>
    <el-tabs>
      <el-tab-pane label="文章" name="first">
        <p class="context" >{{article.context}}</p>
      </el-tab-pane>
      <el-tab-pane label="评论" name="second">

        <!--        显示-->
        <li v-for="(comment,index) in lists" v-bind:key="index">
          <p @click="gotoUser(index)">
            <el-avatar :size="50" :src="comment.userAvatar"></el-avatar>
            <span class="comName">{{comment.userName}}</span>
          </p>
          <p>
            <span class="comText">{{comment.text}}</span>
          </p>
          <p class="comTime">{{comment.createTime}}</p>
        </li>
        <el-form>
          <el-form-item label="发表评论">
            <el-input type="textarea" v-model="form.desc"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">评论</el-button>
          </el-form-item>
        </el-form>

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
      form:{
        userId:-1,
        articleId:-1,
        desc:""
      },
      article:{},
      lists:[]
    }
  },
  created() {
    this.id=Number(this.$route.query.artId);
    this.form.userId=localStorage.getItem("token");;
    this.form.articleId=this.id;
    this.getArticle();
    this.getComment();
  },
  methods:{
    //获取文章
    getArticle(){
      let success=(response)=>{
        this.article=response.data.obj;
      }
      utils.axiosMethod({
        method:"Get",
        url:"/article/"+this.id+"/",
        callback:success
      })
    },
    //获取评论
    getComment(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/comment/article/"+this.id+"/",
        data:this.msg,
        callback:success
      })
    },
    //前往作者页面
    gotoUser(index){
      this.$router.push('/userInfo?userId='+this.lists[index].userId);
    },
    //页码改变
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.msg.size=val;
      this.msg.current=1;
      this.getComment();
    },
    //页码改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.msg.current=val;
      this.getComment();
    },
    //提交评论
    onSubmit(){
      let success=(response)=>{
        window.location.reload()
      }
      if(this.form.userId===null) {
        this.$message.error("需登录才可评论");
      }
      else {
        utils.axiosMethod({
          method:"Post",
          url:"/comment/add/",
          data:this.form,
          callback:success
        })
      }
    }
  }
}
</script>


<style scoped>
.info .block .el-image{
  width: 100%;
  height: 300px;
}
.info img.avater{
  float: left;
  width: 30px;
  margin-bottom: 10px;
  margin-right: 2%;
}
.info .title{
  text-align:center;
  font-size: 30px;
  color: rgb(0, 0, 0);
}
.info .time{
  text-align: right;
  font-size: 12px;
  color: #555;
}
.info .user{
  text-align: center;
  font-size: 18px;
  color: #000000;
}
.info .label{
  text-align: center;
  font-size: 18px;
  color: #000000;
}
.info p{
  text-align: left;
}
.info .comName{
  text-align: left;
  font-size: 12px;
  color: rgb(0, 0, 0);
}
.info .comSize{
  text-align: left;
  font-size: 5px;
  color: rgb(121, 121, 121);
}
.info .comText{
  text-align: left;
  font-size: 12px;
  color: rgb(0, 0, 0);
}
.info .comTime{
  text-align: right;
  font-size: 5px;
  color: rgb(121, 121, 121);
}
.info .el-icon-star-on{
  color: rgb(230, 0, 0);
}
.info .el-icon-star-off{
  color: rgb(230, 0, 0);
}
.info .buy{
  position: fixed;
  left:0;
  bottom: 40px;

  width: 100%;
  height: 28px;
}
</style>
