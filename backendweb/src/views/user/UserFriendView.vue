<template>
    <ContentBase style="margin-top:10px">
       <div class="row">
            <div class="col-3">
                <FriendList :friends="friends" @chatObjectName="ClickName"/>
            </div>
            <div class="col-9">
                <FriendChat :chatObjectName="chatObjectName"/>
            </div>
       </div>
    </ContentBase>
    
</template>
<script>
import ContentBase from '@/components/ContentBase.vue'
import FriendList from '@/components/friend/FriendList.vue'
import FriendChat from '@/components/friend/FriendChat'
import { reactive , ref} from 'vue'

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

        let chatObjectName = ref("");

        const ClickName = (name) => {
            chatObjectName = name;
            console.log(name);
        }

        return {
            ContentBase,
            FriendList,
            FriendChat,
            friends,
            ClickName,
            chatObjectName,
        }
    },
    
}
</script>
<style scoped>

</style>