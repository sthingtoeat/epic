<template>
  <button @click="show_tips" type="button" class="btn btn-primary" id="liveToastBtn">
    Show live toast
  </button>
  <div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div
      id="liveToast"
      class="toast"
      role="alert"
      aria-live="assertive"
      aria-atomic="true"
    >
      <div class="toast-header">
        <img src="../assets/logo.png" class="rounded me-2" alt="..." />
        <strong class="me-auto">Bootstrap</strong>
        <small>11 mins ago</small>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="toast"
          aria-label="Close"
        ></button>
      </div>
      <div class="toast-body">Hello, world! This is a toast message.</div>
    </div>
  </div>
</template>
<script>
import bootstrap from 'bootstrap';
import {ref} from 'vue';
export default {
  setup() {

    
    const show_tips = () =>{
        const toastLiveExample =ref(null);
        const toast = new bootstrap.Toast(toastLiveExample);

        toast.show();
      }
        
    return {
      show_tips,
    }
    
 }   
}
</script>

<style scoped>
</style>