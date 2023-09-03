<template>
    <ContentBase>
       <div class="row">
            <div class="col-3">
                <FriendList :friends="friends"/>
            </div>
            <div class="col-9">
                <FriendChat/>
            </div>
       </div>
    </ContentBase>
    
</template>
<script>
import ContentBase from '@/components/ContentBase.vue'
import FriendList from '@/components/friend/FriendList.vue'
import FriendChat from '@/components/friend/friendChat.vue'
import { reactive } from 'vue'

export default {
    components:{
        ContentBase,
        FriendList,
        FriendChat,
    },
    setup() {
        const friends = reactive({
            count:2,
            friends:[
                {
                    friendId:1,
                    friendName:"qweraaaaaaaaaaa",
                    friendPhoto:"https://cdn.acwing.com/media/user/profile/photo/150655_lg_8d32256772.jpg"
                },
                {
                    friendId:2,
                    friendName:"zxcvb",
                    friendPhoto:"https://cdn.acwing.com/media/user/profile/photo/150655_lg_8d32256772.jpg"

                }
            ]
        });
    
        return {
            ContentBase,
            FriendList,
            FriendChat,
            friends,
        }
    },
    
}
</script>
<style scoped>

</style>