<template>
  <q-card style="width: 700px;max-width: 82vw;">
    <div class="column" style="text-align: center">
      <div class="col" style="text-align: center">
        <q-bar dark class="bg-primary text-white" style="height: 10vh">
          <div class="col text-weight-bold">
            Dejavu估价 准确率高达90%。
          </div>
        </q-bar>
      </div>
      <div class="col" align="center">
        <template>
          <div class="q-pa-md" style="max-width: 400px;" >

            <q-form
              @submit="onSubmit"
              @reset="onReset"
              class="q-gutter-md"
            >
              <q-input
                filled
                v-model="brand"
                label="品牌"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type something']"
              />

              <q-input
                filled
                v-model="fueltype"
                label="燃油类型"
                lazy-rules
                :rules="[
          val => val !== null && val !== '' || 'Please type something ']"
              />

              <q-input
                filled
                v-model="gearbox"
                label="变速箱"
                lazy-rules
                :rules="[
          val => val !== null && val !== '' || 'Please type something']"
              />

              <q-input
                filled
                v-model="power"
                label="马力"
                lazy-rules
                :rules="[
          val => val !== null && val !== '' || 'Please type something']"
              />

              <q-input
                filled
                v-model="kilometer"
                label="已经行驶的公里数"
                lazy-rules
                :rules="[
          val => val !== null && val !== '' || 'Please type something']"
              />

              <q-input
                filled
                v-model="damage"
                label="受损情况"
                lazy-rules
                :rules="[
          val => val !== null && val !== '' || 'Please type something']"
              />

              <div>
                <q-btn label="提交" type="submit" color="primary"/>
                <div>
                  <q-dialog v-model="result">
                    <evaluate-result :hint="hint"/>
                  </q-dialog>
                </div>
                <q-btn label="重置" type="reset" color="primary" flat class="q-ml-sm" />
              </div>
            </q-form>
          </div>
        </template>
      </div>
    </div>
  </q-card>

</template>

<script>
import EvaluateResult from 'components/evaluateResult'
export default {
  name: 'evaluateContent',
  components: { EvaluateResult },
  data () {
    return {
      brand: null,
      fueltype: null,
      gearbox: null,
      power: null,
      kilometer: null,
      damage: null,
      result: false,
      hint: null
    }
  },
  methods: {
    onSubmit () {
      if (this.brand === null || this.fueltype === null || this.gearbox === null || this.power === null || this.kilometer === null ||
        this.damage === null) {
        alert('请填写完表单！')
        return
      }

      const self = this
      this.result = true
      this.$axios.post('/spark/dataming/predict/?brand=' + this.brand + '&fuelType=' + this.fueltype +
        '&gearBox=' + this.gearbox + '&power=' + this.power + '&kilometer=' + this.kilometer + '&unrepairedDamage=' + this.damage).then((res) => {
        self.hint = res.data.result.priceDown.toString() + '~' + res.data.result.priceUp.toString()
      })
    },

    onReset () {
      this.brand = null
      this.fueltype = null
      this.gearbox = null
      this.kilometer = null
      this.damage = null
      this.power = null
    }
  }
}
</script>

<style scoped>

</style>
