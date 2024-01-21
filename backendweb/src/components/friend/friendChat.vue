<template>
    <ContentBase>
        妙妙屋{{chatObject}}
    </ContentBase>
</template>
<script>
import ContentBase from '@/components/ContentBase.vue'
import { watch ,ref} from 'vue'
export default {
    components:{
        ContentBase
    },
    props:{
        chatObjectName:{
            type:Object,
            required:false,
        }
    },
    setup(props) {
        let chatObject = ref("");
        
        watch(
            () => props.chatObjectName,
            (newVal) => {
                chatObject = newVal;
                console.log("新的值为"+newVal);
            }
        )

        return {
          ContentBase,
          chatObject,
      }
    },
}
</script>
<style scoped>

</style>