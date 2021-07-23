<template>
  <div>
    <el-container>
      <el-header>
        <el-header>
          <label>{{article.title}}</label>
        </el-header>
      </el-header>
      <el-container>
        <el-container>
          <el-main>{{article.context}}</el-main>
          <el-footer>{{article.user}}</el-footer>
        </el-container>
      </el-container>
    </el-container>
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
      article:{}
    }
  },
  created() {
    this.id=Number(this.$route.query.artId);
    this.getArticle();
  },
  methods:{
    getArticle(){
      let success=(response)=>{
        this.article=response.data.obj;
      }
      utils.axiosMethod({
        method:"Get",
        url:"/article/"+this.id+"/",
        callback:success
      })
    }
  }
}
</script>

<style>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
