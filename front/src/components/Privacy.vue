<template>
  <div class="privacy">
    <div class="info">
      <p>
        公开我的个人信息
      </p>
      <p>
        <el-switch
          v-model="value1"
          active-text="开"
          inactive-text="关">
        </el-switch>
      </p>
    </div>
    <div class="info">
      <p>
        公开我的收藏夹
      </p>
      <p>
        <el-switch
          v-model="value2"
          active-text="开"
          inactive-text="关">
        </el-switch>
      </p>
    </div>

  </div>
</template>
<script>
  export default {
    data() {
      return {
        value1: true,
        value2: true
      }
    },
    methods:{

    }
  };
</script>
