<template>
  <div>
    <el-form ref="form" label-width="80px">
      <el-form-item label="文章标题">
        <el-input v-model="article.title"></el-input>
      </el-form-item>
      <el-form-item label="文章状态">
        <el-select v-model="article.stateStr" :placeholder="article.stateStr">
          <el-option label="已发布" value="已发布"></el-option>
          <el-option label="未发布" value="未发布"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签设置">
        <el-checkbox-group v-model="article.labels">
          <el-checkbox v-for="(label,index) in labels" :label="label.text" name="labels"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="文章概要">
        <el-input type="textarea" v-model="article.summary"></el-input>
      </el-form-item>
      <el-form-item label="文章内容">
        <el-input type="textarea" v-model="article.context"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button v-if="!isNaN(id)" type="primary" @click="onSubmit">修改</el-button>
        <el-button v-else type="primary" @click="newArticle">新建</el-button>
        <el-button @click="back">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import utils from "../../utils/utils";

export default {
  name: 'Article',

  data()
  {
    return{
      id:-1,
      article:{
        title:"",
        context:"",
        summary:"",
        labels:[],
        userId:-1,
        stateStr:""
      },
      labels:[]
    }
  },
  created() {
    this.id=Number(this.$route.query.artId);
    this.article.userId=localStorage.getItem("token");
    this.getLabel();
    if(this.id)
    this.getArticle();
    else
      this.article.stateStr="未发布";
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
    getLabel(){
      let success=(response)=>{
        this.labels=response.data.list;
      }
      utils.axiosMethod({
        method:"Get",
        url:"/article/labels/",
        callback:success
      })
    },
    onSubmit(){
      if(this.article.stateStr==="未发布")
        this.article.state=0;
      else this.article.state=1;
      // console.log(this.article)
      let success=(response)=>{
        // this.labels=response.data.list;
      }
      utils.axiosMethod({
        method:"Put",
        url:"/article/update",
        data:this.article,
        callback:success
      })
    },
    newArticle(){
      if (!this.article.title) {
        this.$message.error('请输入文章标题');
        return;
      }
      if (!this.article.context) {
        this.$message.error('请输入文章内容');
        return;
      }
      if (!this.article.summary) {
        this.$message.error('请输入文章概要');
        return;
      }
      let success=(response)=>{
        // this.labels=response.data.list;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/newArticle/",
        data:this.article,
        callback:success
      })
    },
    back(){
      this.$router.go(-1);
    }
  }
}
</script>
