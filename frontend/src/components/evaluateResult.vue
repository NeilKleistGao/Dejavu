<template>
  <q-card style="width: 500px; max-width: 80vw;">
    <div class="column">
      <div class="col">
        <q-bar dark class="bg-primary text-white" style="height: 10vh">
          <div class="col text-weight-bold" v-if="hint !== null">
            Dejavu为您预测的价格区间为：
            {{hint}}
          </div>
          <div v-else>
            <q-circular-progress
              indeterminate
              size="50px"
              color="lime"
              class="q-ma-md"
            />
          </div>
        </q-bar>
      </div>
    </div>
  </q-card>
</template>

<script>
export default {
  name: 'evaluateResult',
  props: {
    hint: {
    }
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>

</style>
