<template>
  <div class="info">
    <p>姓名:{{name}}</p>
    <p>邮箱:{{email}}</p>
    <p>电话:{{phone}}</p>
    <p>权限:{{type}}</p>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data()
    {
        return{
          name:"",
          email:"",
          phone:"",
          type:""
        }
    },
    methods:{
      getInfo(){
        axios.get('http://localhost:8081/test',{})
          .then(res=>{
          console.log(res.data)
          var jObject=res.data.valueOf()
          this.name=jObject.name
          this.email=jObject.email
          this.phone=jObject.phone
          if(jObject.type==0)  this.type="普通用户"
          else this.type="管理员"
          console.log(jObject.name)
        }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      }
    },
    created() {
      this.getInfo();
    }
  }
</script>
