import MissingFallback from '@_components/MissingFallback/MissingFallback';
import MoimCardList from '@_components/MoimCardList/MoimCardList';
import MoimCardListSkeleton from '@_components/MoimList/MoimCardListSkeleton/MoimCardListSkeleton';
import useMoims from '@_hooks/queries/useMoims';

export default function MoimList() {
  const { moims, isLoading } = useMoims();

  if (isLoading) {
    return <MoimCardListSkeleton />;
  }

  return moims && moims.length > 0 ? (
    <MoimCardList moimInfos={moims} />
  ) : (
    <MissingFallback text="아직 만들어진 모임이 없어요" />
  );
}
