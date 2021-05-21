<!--
 * @FileDescription: 预约砍价对话框
 * @Author: NeilKleistGao
 * @Date: 2021/5/21
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/21
 -->
<template>
  <div>
    <q-card-section>
      <div class="text-h5">预约买车</div>
    </q-card-section>
    <q-card-section>
      <div>
        <div class="text-subtitle1">买家报价: {{bargain.price}}元</div>
        <q-slider v-model="bargain.price" :min="price * 0.6" :max="price" snap label/>
      </div>

      <div style="margin-top: 1em">
        <div class="text-subtitle1" style="margin-bottom: 1em">请在以下时间范围内联系我：</div>
        <div class="row">
          <div class="col-4"/>
          <div class="col-4">
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
    }
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
