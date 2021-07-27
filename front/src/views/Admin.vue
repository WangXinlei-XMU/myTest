<template>
  <div class="admin">
    <el-tabs :tab-position="tabPosition" style="height: 200px;">
      <el-tab-pane label="文章管理">

        <!--          搜索框-->
        <div class="navbar">
          <label class="labal">文章搜索</label>
          <el-input class="input"
                    placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model.trim="articleMsg.key">
          </el-input>
          <el-button type="primary" icon="el-icon-search" @click="getArticle">搜索</el-button>
        </div>
        <!--          限制条件-->
        <el-collapse>
          <el-collapse-item title="搜索限制" name="1">
            <el-form ref="form" label-width="80px">
              <el-form-item label="作者">
                <el-input v-model.trim="articleMsg.user"></el-input>
              </el-form-item>
              <el-form-item label="状态">
                <el-select v-model="articleMsg.stateStr" :placeholder="articleMsg.stateStr">
                  <el-option label="已发布" value="已发布"></el-option>
                  <el-option label="未发布" value="未发布"></el-option>
                  <el-option label="被封禁" value="被封禁"></el-option>
                  <el-option label="已删除" value="已删除"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="标题">
                <el-input v-model.trim="articleMsg.title"></el-input>
              </el-form-item>
              <el-form-item label="发布时间">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model.trim="articleMsg.date1" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">----</el-col>
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model.trim="articleMsg.date2" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-form-item>
            </el-form>
          </el-collapse-item>
        </el-collapse>
        <!--        显示-->
        <el-table :data="article" style="width: 100%" >
          <el-table-column prop="title" label="标题" width="180"></el-table-column>
          <el-table-column prop="user" label="作者" width="180"></el-table-column>
          <el-table-column prop="labels" label="标签" width="180"></el-table-column>
          <el-table-column prop="createTime" label="创立时间" width="180"></el-table-column>
          <el-table-column prop="modifyTime" label="修改时间" width="180"></el-table-column>
          <el-table-column prop="stateStr" label="状态" width="180"></el-table-column>
          <el-table-column fixed="right" label="操作" width="180">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="banArticle(scope.row)">封禁</el-button>
              <el-button type="text" size="small" @click="unBanArticle(scope.row)">解封</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--          页码-->
        <div class="block">
          <el-pagination
            @size-change="articleHandleSizeChange"
            @current-change="articleHandleCurrentChange"
            :current-page="articleMsg.current"
            :page-sizes="[5, 10, 20]"
            :page-size="articleMsg.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="articleMsg.total">
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="用户管理" name="second">
        <!--        显示-->
        <el-table :data="customer" style="width: 100%" >
          <el-table-column prop="id" label="序号" width="80"></el-table-column>
          <el-table-column prop="name" label="姓名" width="100"></el-table-column>
          <el-table-column prop="password" label="密码" width="100"></el-table-column>
          <el-table-column prop="email" label="邮箱" width="160"></el-table-column>
          <el-table-column prop="phone" label="电话" width="160"></el-table-column>
          <el-table-column prop="type" label="类型" width="60"></el-table-column>
          <el-table-column prop="state" label="状态" width="60"></el-table-column>
          <el-table-column fixed="right" label="操作" width="180">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="banCustomer(scope.row)">封禁</el-button>
              <el-button type="text" size="small" @click="unBanCustomer(scope.row)">解封</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!--          页码-->
        <div class="block">
          <el-pagination
            @size-change="customerHandleSizeChange"
            @current-change="customerHandleCurrentChange"
            :current-page="customerMsg.current"
            :page-sizes="[5, 10, 20]"
            :page-size="customerMsg.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="customerMsg.total">
          </el-pagination>
        </div>
      </el-tab-pane>
      <el-tab-pane label="文章浏览记录">
        <!--        显示-->
        <el-table :data="browse" style="width: 100%" >
          <el-table-column prop="articleTitle" label="文章" width="120"></el-table-column>
          <el-table-column prop="userName" label="用户" width="120"></el-table-column>
          <el-table-column prop="time" label="时间" width="200"></el-table-column>
        </el-table>
        <!--          页码-->
        <div class="block">
          <el-pagination
            @size-change="browseHandleSizeChange"
            @current-change="browseHandleCurrentChange"
            :current-page="browseMsg.current"
            :page-sizes="[5, 10, 20]"
            :page-size="browseMsg.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="browseMsg.total">
          </el-pagination>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>

import utils from "../../utils/utils";

export default {
  name: 'admin',

  data()
  {
    return{
      tabPosition:'top',
      token:1,
      //文章
      article:[],
      articleMsg:{
        key:"",
        size:5,
        current:1,
        total:0,
        user:"",
        title:'',
        state:'',
        stateStr:'',
        date1:'',
        date2:''
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
    this.getArticle();
    this.getUser();
    this.getBrowse();
  },
  methods:{
    //文章相关
    getArticle(){
      let success=(response)=>{
        this.article=response.data.list;
        this.articleMsg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/admin/article/limit/",
        data:this.articleMsg,
        callback:success
      })
    },
    //文章页码改变
    articleHandleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.articleMsg.size=val;
      this.getArticle();
    },
    //文章页码改变
    articleHandleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.articleMsg.current=val;
      this.getArticle();
    },
    //获取用户
    getUser(){
      let success=(response)=>{
        this.customer=response.data.list;
        this.customerMsg.total=response.data.code;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/user/admin/customer",
        data:this.customerMsg,
        callback:success
      })
    },
    //用户页码改变
    customerHandleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.customerMsg.size=val;
      this.getUser();
    },
    //用户页码改变
    customerHandleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.customerMsg.current=val;
      this.getUser();
    },
    //浏览记录
    getBrowse() {
      let success=(response)=>{
        this.browse=response.data.list;
        this.browseMsg.total=response.data.code;
      }
      utils.axiosMethod({
        method:"Post",
        url:"/other/admin/browse",
        data:this.browseMsg,
        callback:success
      })
    },
    //用户页码改变
    browseHandleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.customerMsg.size=val;
      this.getUser();
    },
    //用户页码改变
    browseHandleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.customerMsg.current=val;
      this.getUser();
    },
    //封禁文章
    banArticle(row){
      let artId=row.id;
      let success=(response)=>{

      }
      utils.axiosMethod({
        method:"Put",
        url:"/article/admin/banArticle/"+artId,
        callback:success
      })
    },
    //解封
    unBanArticle(row){
      let artId=row.id;
      let success=(response)=>{

      }
      utils.axiosMethod({
        method:"Put",
        url:"/article/admin/unBanArticle/"+artId,
        callback:success
      })
    },
    //封禁用户
    banCustomer(row){
      let artId=row.id;
      let success=(response)=>{

      }
      utils.axiosMethod({
        method:"Put",
        url:"/customer/admin/banCustomer/"+artId,
        callback:success
      })
    },
    //解封用户
    unBanCustomer(row){
      let artId=row.id;
      let success=(response)=>{

      }
      utils.axiosMethod({
        method:"Put",
        url:"/customer/admin/unBanCustomer/"+artId,
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

