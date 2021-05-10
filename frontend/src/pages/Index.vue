<!--
 * @FileDescription: 主页文件
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: ZoeChou
 * @LastEditTime: 2021/5/9
 -->
<template>
  <q-page class="flex flex-center">
    <!--ADD YOUR VUE CODE HERE-->
    <div class="parent" style="height:80px;"></div>
    <q-form @submit="gotoSearch">
      <div class="parentSearch">
        <div class="child" style="height:70px; width:550px;">
          <q-input outlined v-model="message" label="请输入关键字，例如：君越 2.4L" style="height:50px;"/>
        </div>
        <div class="child">
          <q-btn label="搜索" v-on:click="gotoSearch" type="submit" color="primary" style="width:100px; height:55px;"/>
        </div>
      </div>
    </q-form>
    <br/>
    <div class="parentBuySell">
      <div class="child" style="width:700px">
        <div>
          <q-btn color="blue" icon="fas fa-car" icon-right="send" label="我要买车" v-on:click="gotoSearch"/>
        </div>
        <div class="parent" style="height: 20px"></div>
        <div>
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '大众', value: '大众'},
            {label: '宝马', value: '宝马'},
            {label: '奔驰', value: '奔驰'},
            {label: '丰田', value: '丰田'},
            {label: '本田', value: '本田'},
            {label: '奥迪', value: '奥迪'},
            {label: '别克', value: '别克'},
          ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
              {label: '日产', value: '日产'},
              {label: '福特', value: '福特'},
              {label: '现代', value: '现代'},
              {label: '马自达', value: '马自达'},
              {label: '雪佛兰', value: '雪佛兰'},
              {label: '吉利', value: '吉利'},
              {label: 'Jeep', value: 'Jeep'},
            ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '3万以下', value: '3'},
            {label: '3-5万', value: '3-5'},
            {label: '5-7万', value: '5-7'},
            {label: '7-9万', value: '7-9'},
            {label: '9-12万', value: '9-12'},
            {label: '12-16万', value: '12-16'},
            {label: '16-20万', value: '16-20'},
          ]"
          />
          <q-btn-toggle
            v-on:click="gotoSearch"
            v-model="message"
            spread
            toggle-color="primary"
            :options="[
            {label: '超值', value: '3'},
            {label: '急售', value: '3-5'},
            {label: '练手车', value: '5-7'},
            {label: '准新车', value: '7-9'},
            {label: '严选车', value: '9-12'},
            {label: 'SUV', value: '12-16'},
            {label: '查看更多', value: ''},
          ]"
          />
        </div>
      </div>
      <div class="child" style="width:500px; height:280px;"></div>
    </div>
    <br/>
    <div class="parentQA">
      <div class="child" style="width:800px; height:300px;"></div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',

  data: function () {
    return {
      message: ''
    }
  },

  methods: {
    gotoSearch () {
      this.$router.push({
        path: '/search',
        query: {
          message: this.message
        }
      })
    }
  }
}
</script>

<style>
  .parent, .parentSearch, .parentBuySell, .parentQA {
    display: flex;
    width: 1200px;
    justify-content: center;
    align-content: center;
    align-items: stretch;
  }

  .child {
    width: 100px;
    outline: solid 1px;
  }
</style>
