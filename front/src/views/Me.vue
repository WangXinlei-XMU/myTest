<template>
  <div class="info">
    <el-tabs :tab-position="tabPosition" style="height: 200px;">
      <el-tab-pane label="文章">
        <i class="el-icon-edit" @click="newArticle">新建文章</i>
        <el-table :data="lists" style="width: 100%" :row-class-name="tableRowClassName">
          <el-table-column prop="title" label="标题" width="180"></el-table-column>
          <el-table-column prop="labels" label="标签" width="180"></el-table-column>
          <el-table-column prop="createTime" label="创立时间" width="180"></el-table-column>
          <el-table-column prop="modifyTime" label="修改时间" width="180"></el-table-column>
          <el-table-column prop="stateStr" v-if="" label="状态" width="180"></el-table-column>
          <el-table-column fixed="right" label="操作" width="180">
            <template slot-scope="scope">
              <el-button @click="editClick(scope.row)" type="text" size="small">编辑</el-button>
              <el-button @click="deleteClick(scope.row)" type="text" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
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
      <el-tab-pane label="个人信息" name="second">
        <el-form ref="form" label-width="80px">
          <el-form-item label="姓名">
            <el-input
              v-model="user.name"
              :disabled="true">
            </el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="user.email"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="user.phone"></el-input>
          </el-form-item>
          <el-form-item label="头像">
            <el-upload
              class="avatar-uploader"
              action="user/posts/"
              name="file"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="user.avaterUrl!==null" :src="user.avaterUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="个人简介">
            <el-input type="textarea" v-model="user.introduce"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">修改</el-button>
          </el-form-item>
        </el-form>
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
      user:{},
      lists:[],
      token:1,
      msg:{
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
  },
  methods:{
    getUser(){
      let success=(response)=>{
        this.user=response.data.obj;
      }
      utils.axiosMethod({
        method:"Get",
        url:"/user/"+this.token+"/",
        callback:success
      })
    },
    //
    getArticle(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/user/"+this.token+"/",
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
    //上传图片
    handleAvatarSuccess(res,file) {
      this.user.avatarUrl = res.msg;
      // alert(this.user.avatarUrl);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    //修改个人信息
    onSubmit(){
      let success=(response)=>{
        this.user=response.data.obj;
        if(response.data.code===0)
          this.$message.success(response.data.msg);
        else
          this.$message.error(response.data.msg);
      }
      if(!this.user.email.match("[A-Za-z0-9]+@[A-Za-z0-9]+.com")){
        this.$message.error('邮箱输入有误');
        return;
      }
      if(this.user.phone.length!==11||!this.user.phone.match("[0-9]+")){
        this.$message.error('电话输入有误');
        return;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/user/update/",
        data:this.user,
        callback:success
      })
    },
    //
    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    //进入编辑文章页面
    editClick(row){
      this.$router.push('/article?artId='+row.id);
    },
    //进入编辑文章页面
    newArticle(row){
      this.$router.push('/article');
    },
    //删除文章
    deleteClick(row){
      let success=(response)=>{
        if(response.data.code!==null)
          window.location.reload();
      }
      console.log(row.id)
      utils.axiosMethod({
        method:"Delete",
        url:"/article/delete/"+row.id+"/",
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
</style>

