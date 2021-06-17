<!--
 * @FileDescription: 预约砍价对话框
 * @Author: NeilKleistGao
 * @Date: 2021/5/21
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/21
 -->
<template>
  <div>
    <q-card-section class="bg-primary text-white">
      <div class="text-h5">预约买车</div>
    </q-card-section>
    <q-card-section>
      <div>
        <div class="text-subtitle1" style="margin-left: 0.25em">买家报价: {{bargain.price}}元</div>
        <q-slider v-model="bargain.price" :min="price * 0.6" :max="price" snap label style="margin-left: 10%; width: 80%"/>
      </div>

      <div style="margin-top: 1em">
        <div class="text-subtitle1" style="margin-bottom: 1em; margin-left: 0.25em">请在以下时间范围内联系我：</div>
        <div class="row" style="margin-top: 1em">
          <div style="margin-left: 30%; width: 40%; margin-right: auto">
            <q-date v-model="bargain.time" range/>
          </div>
        </div>
      </div>
    </q-card-section>
  </div>
</template>

<script>
export default {
  name: 'BargainDialog',
  props: {
    value: {
      type: Object,
      required: true
    }, // 砍价数据，使用v-model
    price: {
      type: Number,
      required: true
    } // 车主报价，根据该价格进行砍价
  },
  data () {
    return {
      bargain: this.value // 砍价数据
    }
  },
  watch: {
    bargain: {
      deep: true,
      handler (newValue) {
        this.$emit('input', newValue)
      }
    }
  }
}
</script>

<style scoped>

</style>
