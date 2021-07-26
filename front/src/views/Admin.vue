<template>
  <div class="admin">
    <el-tabs :tab-position="tabPosition" style="height: 200px;">
      <el-tab-pane label="文章管理">

      </el-tab-pane>
      <el-tab-pane label="用户管理" name="second">
      </el-tab-pane>
      <el-tab-pane label="文章浏览记录">
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
      tabPosition:'top',
      token:1,
      //文章
      article:[],
      articleMsg:{
        size:5,
        current:1,
        total:0,
      },
      //浏览相关
      browse:[],
      browseMsg:{
        size:5,
        current:1,
        total:0,
      },
      //用户相关
      customer:[],
      customerMsg:{
        size:5,
        current:1,
        total:0,
      }
    }
  },
  created() {
    this.token=localStorage.getItem("token");
    this.getUser();
    this.getArticle();
    this.getBrowse();
    this.getLike();
  },
  methods:{
    getUser(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/admin/article",
        data:this.msg,
        callback:success
      })
    }
  }
}
</script>


<style scoped>
.el-tabs .el-tab-pane .el-table .warning-row{
  background: #fcedd5;
}
.el-tabs .el-tab-pane .el-table .success-row{
  background: #d9ffc4;
}
.info p{
  text-align: left;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.image {
  width: 100%;
  display: block;
}
</style>

