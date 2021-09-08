<template>
  <q-card style="width: 500px; max-width: 80vw;">
    <div class="column">
      <div class="col">
        <q-bar dark class="bg-primary text-white" style="height: 10vh">
          <div class="col text-weight-bold">
            Dejavu预约卖车，多卖20%，平均7天成交。
            {{hint}}
          </div>
        </q-bar>
      </div>
    </div>
  </q-card>
</template>

<script>
export default {
  name: 'evaluateResult',
  data () {
    return {
      hint: ' '
    }
  }
}
</script>

<style scoped>

</style>
