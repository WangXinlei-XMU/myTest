<template>
  <div>

    <div class="header">
      <h1>欢迎来到Blog</h1>
      <p>创造自己的精彩</p>
    </div>

    <div class="navbar">
      <label class="labal">文章搜索</label>
      <el-input class="input"
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="search">
      </el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchBtn">搜索</el-button>
    </div>
  </div>
</template>
<script>

import utils from "../../utils/utils";

</script>
