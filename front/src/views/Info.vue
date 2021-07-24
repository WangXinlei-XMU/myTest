<template>
  <div>
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
body {
  background:url("https://img-blog.csdnimg.cn/20200516150514343.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3lvdWFyZW15c2NhcmVk,size_16,color_FFFFFF,t_70")  no-repeat center center;   /*加载背景图*/   /* 背景图不平铺 */
  background-size:cover;  /* 让背景图基于容器大小伸缩 */
  background-attachment:fixed;        /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */    //此条属性必须设置否则可能无效/
background-color:#CCCCCC;   /* 设置背景颜色，背景图加载过程中会显示背景色 */
}
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
