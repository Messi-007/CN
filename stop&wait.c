#include <stdio.h>
int main()
{
    int framesize, sent = 0, ack, i;
    printf("enter number of frames\n");
    scanf("%d", &framesize);
    while (1)
    {
        for (i = 0; i < framesize; i++)
        {
            printf("frame %d has been transmitted.\n", sent);
            sent++;
            if (sent == framesize)
                break;
        }

        printf("\n please enter the last acknowledgement received.\n");
        scanf("%d", &ack);
        if (ack >= framesize)
            break;
        else
            sent = ack;
    }
    return 0;
}
